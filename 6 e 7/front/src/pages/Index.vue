<template>
  <q-layout view="lHh Lpr lFf">
    <q-header>
      <q-toolbar>
        <q-btn
          flat
          dense
          round
        />
        <div class="col text-center text-weight-bold text-uppercase text-overline">
          <span>{{ $t('test_register') }}</span>
        </div>
        <q-select
          v-model="lang"
          :options="langOptions"
          dense
          borderless
          emit-value
          map-options
          options-dense
          dark
          style="max-width: 150px; margin-top: -4px;float: right;"
        />
      </q-toolbar>
    </q-header>

    <q-page-container>
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
        <transition-group name="slide-up">
          <div key="list_student_card">
            <ListStudentsCard
              @showStudentTests="showStudentTests"
              :selected_student="selected_student"
            />
          </div>
          <div key="show_student_card" v-if="has_student_selected && !loading">
            <ShowStudentCard
              :selected_student="selected_student"
              :selected_student_tests="selected_student_tests"
              @clearSelectedStudent="clearSelectedStudent"
              @reload="getData()"
            />
          </div>
        </transition-group>
        <router-view 
          @reload="getData()" 
          :selected_student="selected_student ? selected_student : null"
          @showStudentTests="showStudentTests"
        ></router-view>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script>
import Api from '../api/index.js'
import { mapState, mapActions } from 'vuex'
import DialogRegisterStudent from 'src/components/DialogRegisterStudent.vue'
import DialogRegisterStudentTest from 'src/components/DialogRegisterStudentTest.vue'
import ShowStudentCard from 'src/components/ShowStudentCard.vue'
import ListStudentsCard from 'src/components/ListStudentsCard.vue'

export default {
  name: 'PageIndex',
  components: {
    DialogRegisterStudent,
    DialogRegisterStudentTest,
    ShowStudentCard,
    ListStudentsCard
  },
  data () {
    return {
      dialog_register_student: false,
      dialog_register_student_test: false,
      has_student_selected: false,
      selected_student_tests: [],
      selected_student: {},
      lang: this.$i18n.locale,
      langOptions: [
        { value: 'en-US', label: 'English' },
        { value: 'pt-BR', label: 'Portuguese' }
      ]
    }
  },
  mounted () {
    this.getData()
  }, 
  methods: {
    clearSelectedStudent () {
      this.has_student_selected = false
      this.selected_student = {}
      this.selected_student_tests = []
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
    getData(){
      this.loadingStart()
    },
    ...mapActions('students', ['loadingStart','loadingEnd'])
  },
  computed: {
    ...mapState('students', ['loading'])
  },
  watch: {
    lang(lang) {
      this.$i18n.locale = lang
    }
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
.q-field__native, .q-field__prefix, .q-field__suffix, .q-field__input{
  color: white!important;
}
</style>
