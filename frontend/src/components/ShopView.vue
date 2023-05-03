<template>
    <div>
        <shop-list :shops="shops" />
    </div>
</template>

<script>
    export default {
        components: {
          ShopList: () => import('./ShopList.vue')
        },
        name: 'ShopView',
        data() {
            return {
                shops: []
            }
        },
    }
</script>

<style scoped>

</style>