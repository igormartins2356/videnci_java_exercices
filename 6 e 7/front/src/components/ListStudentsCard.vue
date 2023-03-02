<template>
    <q-card style="width: 300px" key="students" v-if="!loading">
        <q-card-section>
        <q-list>
            <q-item-label header>
                <div class="row justify-start" style="margin-top: -20px;">
                    <span class="q-mt-xs">Maior nota: {{ maximun_grade }}</span>
                </div>
                <q-separator class="q-mt-sm"/>
                <div class="row justify-between">
                    <span class="q-mt-xs">Estudantes</span>
                    <q-btn size="sm" @click="$router.push({name: 'RegisterStudent'})" dense flat label="Adicionar Aluno" icon="add" />
                </div>
            </q-item-label>

            <q-item :style="selected_student.id == student.id ? 'background-color: rgba(0,0,0,0.1);' : ''" @click="showStudentTests(student)" v-for="(student,idx) in students" :key="idx" :clickable="selected_student.id != student.id" v-ripple="selected_student.id != student.id">
                <q-item-section avatar top>
                    <q-avatar icon="person" color="primary" text-color="white" />
                </q-item-section>

                <q-item-section>
                    <q-item-label lines="1">{{ student.name }}</q-item-label>
                </q-item-section>

                <q-item-section side>
                    <q-icon v-if="selected_student.id == student.id" name="chevron_left" color="grey" flat round/>
                </q-item-section>
            </q-item>

        </q-list>
        </q-card-section>
    </q-card>
</template>

<script>
import Api from '../api/index.js'
import { mapState,mapActions } from 'vuex'

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
      students: [],
      maximun_grade: 0
    }
  },
  methods: {
    showStudentTests (student) {
      this.$emit('showStudentTests', student)
    },

    async getStudents () {
      return await Api.Students.index().then((r) => {
        this.students = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        // setTimeout(() => {
        //   this.loading = false
        // }, 600)
      })
    },
    async getMaximunGrade () {
      return await Api.Tests.showMaximumGrade().then((r) => {
        this.maximun_grade = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        // setTimeout(() => {
        //   this.loading = false
        // }, 600)
      })
    },
    async getData () {
      await this.getStudents()
      await this.getMaximunGrade()
      setTimeout(() => {
        this.loadingEnd()
      }, 600)
    },
    ...mapActions('students', ['loadingStart','loadingEnd'])
  },
  mounted () {
    this.getData()
  },
  computed: {
    ...mapState('students', ['loading'])
  },
  watch: {
    loading (od, nv) {
      if(od == true){
        console.log(nv)
        this.getData()
      }
    }
  }
}
</script>
