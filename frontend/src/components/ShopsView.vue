<template>
    <div>
        <b-form inline>
            <label class="sr-only" for="inline-form-input-name">Shop</label>
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

                <b-form-input
                    v-model="lastname"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Фамилия"
                ></b-form-input>

                <b-form-input
                    v-model="dname"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Имя"
                ></b-form-input>

                <b-form-input
                    v-model="fathername"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Отчество"
                ></b-form-input>

                <b-form-input
                    v-model="birth"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Дата рождения"
                ></b-form-input>

                <b-form-input
                    v-model="phone"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Номер телефона"
                ></b-form-input>

                <b-form-input
                    v-model="email"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Адрес эл.почты"
                ></b-form-input>

                <b-button variant="primary" @click="save">Сохранить</b-button>
            </b-form>

            <div>
                <h2> {{ shop.name }} </h2>
                <span>
                   <b-button variant="primary" @click="edit">Изменить</b-button>
                </span>
                <p>
                    Специализация: {{ shop.info }} <br>
                    Адрес: {{ shop.address }} <br>
                </p>
                <h3> Директор </h3>
                <p>
                    ФИО: {{shop.director.lastname}} {{shop.director.name}} {{shop.director.fathername}} <br>
                    Дата рождения: {{ shop.director.birth }} <br>
                    Адрес эл. почты: {{ shop.director.email }} <br>
                    Номер телефона: {{ shop.director.phone }}
                </p>
            </div>
    </div>
</template>
<script>
    import {url} from "@/main"
    export default {
        name: 'ShopsView',
        data() {
            id: null,
            name: '',
            info: '',
            address: '',
            did: null,
            dname: '',
            lastname: '',
            fathername: '',
            birth: '',
            email: '',
            phone: ''
            director: null,
            shop: null
        },
        created() {
            this.getData()
        },
        methods: {
            getData() {
                this.$http.get(url + "/shop").then(response => {
                    const shops = response && response.data ? response.data : []
                    const shop = shops[0]
                    this.shop = shop.map(r => {
                        return {
                            id: r.id,
                            name: r.name,
                            address: r.address,
                            info: r.info,
                            director: {
                                id: r.director.id,
                                name: r.director.name,
                                lastname: r.director.lastname,
                                fathername: r.director.fathername,
                                birth: r.director.birth,
                                email: r.director.email,
                                phone: r.director.phone
                            }
                        }
                    })
                })
            },
            save() {
                var director = {
                    name: this.dname,
                    lastname: this.lastname,
                    fathername: this.fathername,
                    birth: this.birth,
                    email: this.email,
                    phone: this.phone
                };
                if (this.did) {
                this.$http.put(url + "/seller/" + this.did.toString(), director).then(response => {
                   const director = response && response.data ? response.data : null
                   this.director = director.map(r => {
                        return {
                            id: r.director.id,
                            name: r.director.name,
                            lastname: r.director.lastname,
                            fathername: r.director.fathername,
                            birth: r.director.birth,
                            email: r.director.email,
                            phone: r.director.phone
                        }
                   })
                   )
                })
                } else {
                    this.$http.post(url + "/seller", director).then(response => {
                       const director = response && response.data ? response.data : null
                       this.director = director.map(r => {
                            return {
                                id: r.director.id,
                                name: r.director.name,
                                lastname: r.director.lastname,
                                fathername: r.director.fathername,
                                birth: r.director.birth,
                                email: r.director.email,
                                phone: r.director.phone
                            }
                       })
                       )
                    })
                }
                var shop = {
                    name: this.name,
                    info: this.info,
                    address: this.address,
                    director: this.director
                };
                if (this.id) {
                    this.$http.put(url + "/shop/" + this.id.toString(), shop).then(response => {
                       const shop = response && response.data ? response.data : null
                       this.shop = shop.map(r => {
                            return {
                                id: r.id,
                                name: r.name,
                                address: r.address,
                                info: r.info,
                                director: {
                                    id: r.director.id,
                                    name: r.director.name,
                                    lastname: r.director.lastname,
                                    fathername: r.director.fathername,
                                    birth: r.director.birth,
                                    email: r.director.email,
                                    phone: r.director.phone
                                }
                            }
                       })
                       )
                    })
                } else {
                    this.$http.post(url + "/shop", shop).then(response => {
                       const shop = response && response.data ? response.data : null
                        this.shop = shop.map(r => {
                           return {
                               id: r.id,
                               name: r.name,
                               address: r.address,
                               info: r.info,
                               director: {
                                   id: r.director.id,
                                   name: r.director.name,
                                   lastname: r.director.lastname,
                                   fathername: r.director.fathername,
                                   birth: r.director.birth,
                                   email: r.director.email,
                                   phone: r.director.phone
                               }
                           }
                       })
                       )
                    })

                }
            },
            edit() {
                this.name = this.shop.name;
                this.id = this.shop.id;
                this.address = this.shop.address;
                this.info = this.shop.info;
                this.dname = this.shop.director.name;
                this.did = this.shop.director.id;
                this.lastname = this.shop.director.lastname;
                this.fathername = this.shop.director.fathername;
                this.birth = this.shop.director.birth;
                this.phone = this.shop.director.phone;
                this.email = this.shop.director.email;
            }
        }
    }
</script>
<style scoped>
</style>