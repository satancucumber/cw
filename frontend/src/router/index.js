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
            //meta: {layout: 'main'},
            component: () => import('../components/ProductsView.vue')
        },
//        {
//            path: '/shop',
//            name: 'shop',
//            //meta: {layout: 'main'},
//            component: () => import('../components/ShopView.vue')
//        },
        {
            path: '/shops',
            name: 'shops',
            //meta: {layout: 'main'},
            component: () => import('../components/ShopsView.vue')
        },
    ]
})
export default router