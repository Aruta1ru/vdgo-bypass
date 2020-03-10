import Vue from 'vue'
import VueRouter from 'vue-router'
import Datatable from 'pages/Datatable.vue'

Vue.use(VueRouter)

const routes = [
    { path: '*', component: Datatable },
    { path: '/', component: Datatable}
]

export default new VueRouter({
 /*mode: 'history',  убирает решетку в адресе url,
 однако при перезагрузке возникает ошибка 404, нужна соотвествующая конфигурация сервера,
 которая при нажатии F5 будет возвращать либо главную , либо текущую вкладку */
 routes
})