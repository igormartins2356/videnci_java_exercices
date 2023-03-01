<template>
  <q-page class="flex flex-center">
    <q-circular-progress
      v-if="loading"
      indeterminate
      size="50px"
      :thickness="0.22"
      rounded
      color="primary"
      track-color="grey-3"
      class="q-ma-md"
    />
    <div class="q-pa-md q-gutter-md" v-if="!loading">
      <transition-group name="slide-up">
        <q-card style="width: 300px" key="students">
          <q-card-section>
            <q-list>
              <q-item-label header>
                <div class="row justify-start" style="margin-top: -20px;">
                  <span class="q-mt-xs">Maior nota: {{ maximun_grade }}</span>
                </div>
                <q-separator class="q-mt-sm"/>
                <div class="row justify-between">
                  <span class="q-mt-xs">Estudantes</span>
                  <q-btn size="sm" @click="dialog_register_student = true" dense flat label="Adicionar Aluno" icon="add" />
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
        <q-card style="width: 300px" v-if="has_student_selected" key="students-tests" class="q-mt-sm">
          <q-card-section class="row justify-center">
            <div class="text-h6" style="margin-top: -5px;">{{ selected_student.name }}</div>
            <q-space />
            <div>
              <q-btn color="negative" size="sm" @click="deleteStudent(selected_student.id)" dense flat label="Remover estudante" icon="remove" />
            </div>
          </q-card-section>

          <q-card-section class="q-pt-none">
            <q-list bordered padding class="rounded-borders" style="max-width: 350px">
              <div class="row justify-start">
                <q-btn color="primary" size="sm" @click="openDialogRegisterStudentTest()" dense flat label="Adicionar prova" icon="add" />
              </div>
              <q-item-label header>Notas de {{ selected_student.name }}</q-item-label>

              <q-item>
                <q-item-section>
                  <q-item-label lines="1"><span class="text-grey">Nota Média: </span> {{ selected_student_tests[0] }}</q-item-label>
                  <q-separator class="q-mt-sm"/>
                </q-item-section>
              </q-item>
              <q-item>
                <q-item-section>
                  <q-item-label lines="1"><span class="text-grey">Nota Máxima: </span> {{ selected_student_tests[1] }}</q-item-label>
                  <q-separator class="q-mt-sm"/>
                </q-item-section>
              </q-item>
              <q-item>
                <q-item-section>
                  <q-item-label lines="1"><span class="text-grey">Nota Mínima: </span> {{ selected_student_tests[2] }}</q-item-label>
                  <q-separator class="q-mt-sm"/>
                </q-item-section>
              </q-item>
              <q-item>
                <q-item-section>
                  <q-item-label lines="1"><span class="text-grey">Provas realizadas: </span> {{ selected_student_tests[3] }}</q-item-label>
                  <q-separator class="q-mt-sm"/>
                </q-item-section>
              </q-item>

            </q-list>
          </q-card-section>

          <q-card-actions align="right" class="bg-white text-teal">
            <q-btn flat label="Fechar" @click="clearSelectedStudent()" />
          </q-card-actions>
        </q-card>
      </transition-group>
    </div>
    <q-dialog v-model="dialog_register_student">
      <q-card>
        <q-form
            @submit="registerStudent()"
            @reset="cancelRegisterStudent()"
            class="q-gutter-md"
          >
          <q-card-section>
              <q-input
                filled
                v-model="new_student.name"
                label="Your name *"
                hint="Name and surname"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type something']"
              />
          </q-card-section>
          <q-card-actions align="right" class="bg-white text-teal">
            <div>
              <q-btn label="Submit" type="submit" color="primary"/>
              <q-btn label="Cancelar" type="reset" color="error" flat class="q-ml-sm" />
            </div>
          </q-card-actions>

        </q-form>
      </q-card>
    </q-dialog>
    <q-dialog v-model="dialog_register_student_test">
      <q-card>
        <q-form
            @submit="registerStudentTest()"
            @reset="cancelRegisterStudentTest()"
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
              <q-btn label="Cancelar" type="reset" color="error" flat class="q-ml-sm" />
            </div>
          </q-card-actions>

        </q-form>
      </q-card>
    </q-dialog>
  </q-page>
</template>

<script>
import Api from '../api/index.js'
import 'quasar-extras/animate/fadeIn.css'
import 'quasar-extras/animate/fadeOut.css'

export default {
  name: 'PageIndex',
  data () {
    return {
      new_student: {
        name: ''
      },
      new_test: {
        grade: ''
      },
      students: [],
      loading: false,
      dialog_register_student: false,
      dialog_register_student_test: false,
      has_student_selected: false,
      selected_student_tests: [],
      selected_student: {},
      is_add_test_field_visible: false,
      maximun_grade: 0
    }
  },
  methods: {
    cancelRegisterStudent () {
      this.new_student.name = ''
      this.dialog_register_student = false
    },
    clearSelectedStudent () {
      this.has_student_selected = false
      this.selected_student = {}
      this.selected_student_tests = []
    },
    openDialogRegisterStudentTest () {
      this.dialog_register_student_test = true
    },
    cancelRegisterStudentTest () {
      this.new_test.grade = ''
      this.dialog_register_student_test = false
    },
    showStudentTests (s) {
      this.selected_student = s
      Api.Students.indexStudentTests(s.id).then((r) => {
        this.selected_student_tests = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.has_student_selected = true
      })
    },
    registerStudentTest () {
      this.dialog_register_student_test = false
      this.loading = true
      const test = {
        student_id: this.selected_student.id,
        ...this.new_test
      }
      Api.Students.createStudentTest(test).then((r) => {
        // this.students = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.new_test.grade = ''
        if (this.selected_student) {
          this.showStudentTests(this.selected_student)
        }
        this.getData()
      })
    },
    registerStudent () {
      this.dialog_register_student = false
      this.loading = true
      Api.Students.create(this.new_student).then((r) => {
        // this.students = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.new_student.name = ''
        this.getData()
      })
    },
    deleteStudent (id) {
      this.has_student_selected = false
      this.loading = true
      Api.Students.delete(id).then((r) => {
        // this.students = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.getData()
      })
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
      this.loading = true
      await this.getStudents()
      await this.getMaximunGrade()
      setTimeout(() => {
        this.loading = false
      }, 600)
    }
  },
  mounted () {
    this.getData()
  }
}
</script>
<style scoped>
.slide-up-move,
.slide-up-enter-active,
.slide-up-leave-active {
  transition: all 0.5s ease;
}

.slide-up-enter,
.slide-up-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
.slide-up-leave-active {
  position: absolute;
}
</style>
