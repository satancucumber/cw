<template>
    <div>
            <shop-form :shops="shops"></shop-form>
            <shop-row v-for="(shop) in mutableshops"
                v-bind:key="shop.id"
                :shop="shop"
                :editShop="editMethod"
                :shops="mutableshops" ></shop-row>
    </div>
</template>

<script>
    import {url} from "@/main";
    export default {
        components: {
          ShopForm: () => import('./ShopForm.vue'),
          ShopRow: () => import('./ShopRow.vue'),
        },
        name: 'ShopListView',
        props: ["shops"],
        data() {
            return {
                shop: null,
                mutableshops: this.shops
            }
        },
        created() {
            this.getData()
        },
        methods: {
            getData() {
                this.$http.get(url + "/shop").then(response => {
                   const shops = response && response.data ? response.data : []
                   this.mutableshops = shops.map(r => {
                     return {
                       id: r.id,
                       name: r.name,
                       address: r.address,
                       director: r.director
                     }
                   })
                 })
           },
            editMethod: function(shop) {
                this.shop = shop;
            }
        }
    }
</script>

<style scoped>

</style>