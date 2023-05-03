<template>
    <div>
        <b-form inline>
            <label class="sr-only" for="inline-form-input-name">Name</label>
                <b-form-input
                    v-model="name"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Название"
                ></b-form-input>

                <b-form-input
                    v-model="info"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Специализация"
                ></b-form-input>

                <b-form-input
                    v-model="address"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Адрес"
                ></b-form-input>

                <director-form :director="director" />

                <b-button variant="primary" @click="save">Сохранить</b-button>
            </b-form>
    </div>
</template>
<script>
    import {url} from "@/main";
    export default {
        components: {
          DirectorForm: () => import('./DirectorForm.vue')
        },
        props: ['shops'],
        name: 'ShopFormView',
        data() {
            return {
                id: null,
                name: '',
                info: '',
                address: '',
                director: null,
                mutableshops: this.shops
            }
        },
        methods: {
            getIndex(list, id) {
                for (var i = 0; i < list.length; i++ ) {
                    if (list[i].id === id) {
                        return i;
                    }
                }
                return -1;
            },
            save() {
                var shop = {
                    name: this.name,
                    info: this.info,
                    address: this.address,
                    director: this.director
                };
                if (this.id) {
                    this.$http.put(url + "/shop/" + this.id.toString(), shop).then(response => {
                       const shop = response && response.data ? response.data : null
                       var index = this.getIndex(this.mutableshops, shop.map(r => {
                            return {
                                id: r.id,
                                name: r.name,
                                address: r.address,
                                info: r.info,
                                director: r.director
                            }
                       }).id);
                       this.mutableshops.splice(index, 1, shop.map(r => {
                            return {
                                id: r.id,
                                name: r.name,
                                address: r.address,
                                info: r.info,
                                director: r.director
                            }
                       })
                       )
                     })
                } else {
                    this.$http.post(url + "/shop", shop).then(response => {
                       const shop = response && response.data ? response.data : null
                       this.mutableshops.push(shop.map(r => {
                            return {
                                id: r.id,
                                name: r.name,
                                address: r.address,
                                info: r.info,
                                director: r.director
                            }
                       })
                       )
                     })
                }
            }
        }
    }
</script>
<style>
</style>
