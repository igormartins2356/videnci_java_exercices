<template>
    <q-dialog v-model="dialog">
        <q-card>
            <q-form
                @submit="registerStudent()"
                @reset="closeRegisterStudent()"
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
                    <q-btn label="Cancelar" @click="closeRegisterStudent()" color="error" flat class="q-ml-sm" />
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
  },
  data () {
    return {
      dialog: true,
      new_student: {
        name: ''
      },
      leftDrawerOpen: false
    }
  },
  methods: {
    registerStudent () {
      Api.Students.create(this.new_student).then((r) => {
        // this.students = r.data
      }).catch((err) => {
        console.log(err)
      }).finally(() => {
        this.$emit('reload')
        this.closeRegisterStudent()
      })
    },
    closeRegisterStudent () {
      this.new_student.name = ''
      this.dialog = false
      setTimeout(()=>{this.$router.push({name: 'Home'})},250)
    }
  },
  watch: {
  }
}
</script>
