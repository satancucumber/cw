<template>
    <div>
        <b-form inline id="form">
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
                <h2> {{ shops[0].name }} </h2>
                <span>
                   <b-button variant="primary" @click="edit">Изменить</b-button>
                </span>
                <p>
                    Специализация: {{ shops[0].info }} <br>
                    Адрес: {{ shops[0].address }} <br>
                </p>
                <h3> Директор </h3>
                <p>
                    ФИО: {{shops[0].director.lastname}} {{shops[0].director.name}} {{shops[0].director.fathername}} <br>
                    Дата рождения: {{ shops[0].director.birth }} <br>
                    Адрес эл. почты: {{ shops[0].director.email }} <br>
                    Номер телефона: {{ shops[0].director.phone }}
                </p>
            </div>
    </div>
</template>
<script>
    import {url} from "@/main"
    export default {
        name: 'ShopsView',
        data() {
            return {
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
                phone: '',
                shops: [
                    {
                        id: null,
                        name: '',
                        info: '',
                        address: '',
                        director: {
                            id: null,
                            name: '',
                            lastname: '',
                            fathername: '',
                            birth: '',
                            email: '',
                            phone: ''
                        }
                    }
                ]
            }
        },
        created() {
            this.getData()
        },
        methods: {
            getData() {
                this.$http.get(url + "/shop").then(response => {
                    const shops = response && response.data ? response.data : []
                    this.shops = shops.map(r => {
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
            clean() {
                  document.getElementById("form").style.display = "none";
                  this.name = '';
                  this.id = null;
                  this.address = '';
                  this.info = '';
                  this.dname = '';
                  this.did = null;
                  this.lastname = '';
                  this.fathername = '';
                  this.birth = '';
                  this.phone = '';
                  this.email = '';
            },
            save() {
                var shop = {
                    name: this.name,
                    info: this.info,
                    address: this.address,
                    director: {
                        id: this.did,
                        name: this.dname,
                        lastname: this.lastname,
                        fathername: this.fathername,
                        birth: this.birth,
                        email: this.email,
                        phone: this.phone
                    }
                };
                if (this.id) {
                    this.$http.put(url + "/shop/" + this.id.toString(), shop).
                        then(()=>this.clean()).
                        then(()=>this.getData())

                } else {
                    this.$http.post(url + "/shop", shop).
                        then(()=>this.clean()).
                        then(()=>this.getData())
                }
            },
            edit() {
                document.getElementById("form").style.display = "block";
                this.name = this.shops[0].name;
                this.id = this.shops[0].id;
                this.address = this.shops[0].address;
                this.info = this.shops[0].info;
                this.dname = this.shops[0].director.name;
                this.did = this.shops[0].director.id;
                this.lastname = this.shops[0].director.lastname;
                this.fathername = this.shops[0].director.fathername;
                this.birth = this.shops[0].director.birth;
                this.phone = this.shops[0].director.phone;
                this.email = this.shops[0].director.email;
            }
        }
    }
</script>
<style scoped>
    #form {
        display: none;
    }
</style>