import Vue from 'vue'
import VueRouter from 'vue-router'
import DataTable from 'pages/DataTable.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: DataTable },
   { path: '*', component:  DataTable  }

]

export default new VueRouter({
mode: 'history',
 routes
})