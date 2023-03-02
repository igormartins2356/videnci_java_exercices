import Http from '../http'

export default {

  showMaximumGrade () {
    return Http.get('/tests/maximum_grade')
  }

}
