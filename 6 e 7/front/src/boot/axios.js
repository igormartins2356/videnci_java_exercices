// eslint-disable-next-line import/default
import Vue from 'vue'
import axios from 'axios'
import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)
Vue.axios.defaults.baseURL = 'http://localhost:8080/api/v1'

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/v1',
  // withCredentials: true,
  headers: {
    'Content-Type': 'application/json'
  }
})

export default { axiosInstance }
