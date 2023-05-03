import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../components/HomeView.vue'

Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/products',
            name: 'products',
            component: () => import('../components/ProductsView.vue')
        },
        {
            path: '/shops',
            name: 'shops',
            component: () => import('../components/ShopsView.vue')
        },
        {
            path: '/sellers',
            name: 'sellers',
            component: () => import('../components/SellersView.vue')
        }
    ]
})
export default router