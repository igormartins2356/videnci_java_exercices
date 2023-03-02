
const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('pages/Index.vue'),
    children: [
      {
        path: '/register_student',
        name: 'RegisterStudent',
        component: () => import('components/DialogRegisterStudent.vue'),
      },
      {
        path: '/register_student_test',
        name: 'RegisterStudentTest', 
        component: () => import('components/DialogRegisterStudentTest.vue'),
      }
    ] 
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
