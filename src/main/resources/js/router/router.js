import Vue from 'vue'
import VueRouter from 'vue-router'
import BypassList from 'pages/BypassList.vue'
import Analytics from 'pages/Analytics.vue'

Vue.use(VueRouter)

const routes = [
    { path: '/', component: BypassList },
    { path: '/analytics', component: Analytics },
   { path: '*', component: BypassList  }
]

export default new VueRouter({
 /*mode: 'history',  убирает решетку в адресе url,
 однако при перезагрузке возникает ошибка 404, нужна соотвествующая конфигурация сервера,
 которая при нажатии F5 будет возвращать либо главную , либо текущую вкладку */
 routes
})