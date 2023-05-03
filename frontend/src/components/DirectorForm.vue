<template>
    <div>
        <b-form inline>
            <label class="sr-only" for="inline-form-input-name">Директор</label>
                <b-form-input
                    v-model="lastname"
                    id="inline-form-input-name"
                    class="mb-2 mr-sm-2 mb-sm-0"
                    placeholder="Фамилия"
                ></b-form-input>

                <b-form-input
                    v-model="name"
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
    </div>
</template>
<script>
    import {url} from "@/main";
    export default {
        props: ['director'],
        name: 'DirectorFormView',
        data() {
            return {
                id: null,
                name: '',
                lastname: '',
                fathername: '',
                birth: '',
                phone: '',
                email: '',
                mutabledirector: this.director
            }
        },
        methods: {
            save() {
                var staff = {
                    name: this.name,
                    lastname: this.lastname,
                    fathername: this.fathername,
                    birth: this.birth,
                    phone: this.phone,
                    email: this.email
                };
                if (this.id) {
                    this.$http.put(url + "/director/" + this.id.toString(), staff).then(response => {
                         const director = response && response.data ? response.data : null
                         this.mutabledirector = director.map(r => {
                           return {
                             id: r.id,
                             name: r.name,
                             lastname: r.lastname,
                             fathername: r.fathername,
                             birth: r.birth,
                             email: r.email,
                             phone: r.phone
                           }
                         })
                       })
                } else {
                    this.$http.post(url + "/director", staff).then(response => {
                          const director = response && response.data ? response.data : null
                          this.mutabledirector = director.map(r => {
                            return {
                              id: r.id,
                              name: r.name,
                              lastname: r.lastname,
                              fathername: r.fathername,
                              birth: r.birth,
                              email: r.email,
                              phone: r.phone
                            }
                          })
                        })
                }
            }
        }
    }
</script>
<style>
</style>
