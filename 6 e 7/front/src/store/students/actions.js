/*
export function someAction (context) {
}
*/
export function loadingStart (context) {
    context.commit('loadingStart')
  }
  
  export function loadingEnd (context) {
    context.commit('loadingEnd')
  }
  