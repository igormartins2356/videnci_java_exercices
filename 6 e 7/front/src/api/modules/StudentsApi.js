import Http from '../http'

export default {

  index (filter) {
    return Http.get('/students', { params: { ...filter } })
  },

  create (student) {
    return Http.post('/students', { ...student })
  },

  createStudentTest (test) {
    return Http.post(`/students/${test.student_id}/tests`, { grade: test.grade })
  },

  update (student) {
    return Http.put('/students', {
      student: {
        ...student
      }
    })
  },

  indexStudentTests (id) {
    return Http.get(`/students/${id}/tests`)
  },

  show (id) {
    return Http.get(`/students/${id}`)
  },

  delete (id) {
    return Http.delete(`/students/${id}`)
  }

}
