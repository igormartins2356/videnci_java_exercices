<template>
    <q-dialog v-model="dialog">
      <q-card>
        <q-form
            @submit="registerStudentTest()"
            @reset="closeDialogRegisterStudentTest()"
            class="q-gutter-md"
          >
          <q-card-section>
              <q-input
                filled
                v-model="new_test.grade"
                label="Grade *"
                hint="Test grade"
                lazy-rules
                :rules="[ val => val && val.length > 0 && val <= 10 || 'Digite alguma nota entre 0 e 10']"
              />
          </q-card-section>
          <q-card-actions align="right" class="bg-white text-teal">
            <div>
              <q-btn label="Submit" type="submit" color="primary"/>
              <q-btn label="Cancelar" @click="closeDialogRegisterStudentTest()" color="error" flat class="q-ml-sm" />
            </div>
          </q-card-actions>

        </q-form>
      </q-card>
    </q-dialog>
</template>

<script>
import Api from '../api/index.js'

export default {
  name: 'DialogRegisterStudent',
  props: {
    selected_student: {
      type: Object,
      default: () => {}
    }
  },
  data () {
    return {
      dialog: true,
      new_test: {
        grade: ''
      },
      leftDrawerOpen: false
    }
  },
  methods: {
    registerStudentTest () {
      const test = {
        student_id: this.selected_student.id,
        ...this.new_test
      }
      Api.Students.createStudentTest(test).then((r) => {
        // this.students = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.$emit('showStudentTests', this.selected_student)
        this.closeDialogRegisterStudentTest()
      })
    },
    closeDialogRegisterStudentTest () {
      this.new_test.grade = ''
      this.dialog = false
      setTimeout(()=>{this.$router.push({name: 'Home'})},250)
    }
  },
  watch: {
  }
}
</script>
