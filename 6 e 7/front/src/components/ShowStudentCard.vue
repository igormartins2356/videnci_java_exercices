<template>
    <q-card style="width: 300px" key="students-tests" class="q-mt-sm">
        <q-card-section class="row justify-center">
            <div class="text-h6" style="margin-top: -5px;">{{ selected_student.name }}</div>
            <q-space />
            <div>
                <q-btn color="negative" size="sm" @click="deleteStudent(selected_student.id)" dense flat :label="$t('remove_student')" icon="remove" />
            </div>
        </q-card-section>

        <q-card-section class="q-pt-none">
            <q-list bordered padding class="rounded-borders" style="max-width: 350px">
                <div class="row justify-start">
                    <q-btn color="primary" size="sm" @click="$router.push({name: 'RegisterStudentTest'})" dense flat :label="$t('add_test')" icon="add" />
                </div>
                <q-item-label header>{{ $t('grades_from') }} {{ selected_student.name }}</q-item-label>

                <q-item>
                    <q-item-section>
                        <q-item-label lines="1"><span class="text-grey">{{$t('grade_average')}}: </span> {{ selected_student_tests[0] }}</q-item-label>
                        <q-separator class="q-mt-sm"/>
                    </q-item-section>
                </q-item>
                <q-item>
                    <q-item-section>
                        <q-item-label lines="1"><span class="text-grey">{{ $t('highest_grade') }}: </span> {{ selected_student_tests[1] }}</q-item-label>
                        <q-separator class="q-mt-sm"/>
                    </q-item-section>
                </q-item>
                <q-item>
                    <q-item-section>
                        <q-item-label lines="1"><span class="text-grey">{{ $t('lower_grade') }}: </span> {{ selected_student_tests[2] }}</q-item-label>
                        <q-separator class="q-mt-sm"/>
                    </q-item-section>
                </q-item>
                <q-item>
                    <q-item-section>
                        <q-item-label lines="1"><span class="text-grey">{{ $t('tests_carried_out') }}: </span> {{ selected_student_tests[3] }}</q-item-label>
                        <q-separator class="q-mt-sm"/>
                    </q-item-section>
                </q-item>

            </q-list>
        </q-card-section>

        <q-card-actions align="right" class="bg-white text-teal">
            <q-btn flat label="Fechar" @click="clearSelectedStudent()" />
        </q-card-actions>
    </q-card>
</template>

<script>
import Api from '../api/index.js'

export default {
  name: 'DialogRegisterStudent',
  props: {
    selected_student: {
      type: Object,
      default: () => {}
    },
    selected_student_tests: {
      type: Array,
      default: () => []
    }
  },
  data () {
    return {
    }
  },
  methods: {
    deleteStudent (id) {
      this.has_student_selected = false
      Api.Students.delete(id).then((r) => {
        // this.students = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.$emit('reload')
        this.$emit('clearSelectedStudent')
      })
    },
    openDialogRegisterStudentTest () {
      this.$emit('openDialogRegisterStudentTest')
    },
    clearSelectedStudent () {
      this.$emit('clearSelectedStudent')
    }
  },
  watch: {
  }
}
</script>
