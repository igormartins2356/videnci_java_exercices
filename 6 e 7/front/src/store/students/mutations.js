/*
export function someMutation (state) {
}
*/
export function loadingStart (state) {
    state.loading = true
  }
  
  export function loadingEnd (state) {
    state.loading = false
  }
  
  export function updateLoadingVal (state, val) {
    state.loading = val
  }
  