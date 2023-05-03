import Vue from 'vue'
import App from './App.vue'
import router from './router'
import http from "@/plugins/http";
import 'materialize-css/dist/js/materialize.min.js'

import VueGoodTablePlugin from 'vue-good-table';
// import the styles
import 'vue-good-table/dist/vue-good-table.css'

import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
Vue.use(VueGoodTablePlugin)

//import 'bootstrap/dist/css/bootstrap.css'
//import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

//import DirectorForm from './components/DirectorForm.vue'
//Vue.use(DirectorForm)
//import ShopForm from './components/ShopForm.vue'
//Vue.use(ShopForm)
//import DirectorRow from './components/DirectorRow.vue'
//Vue.use(DirectorRow)
//import ShopRow from './components/ShopRow.vue'
//Vue.use(ShopRow)
//import ShopList from './components/ShopList.vue'
//Vue.use(ShopList)

Vue.use(http, {
    baseUrl: "http://localhost:8080/api/v1"
})
Vue.prototype.$baseUrl = "http://localhost:8080/api/v1"
export const url = Vue.prototype.$baseUrl

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')