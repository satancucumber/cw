<template>
    <div>
        <i>({{ mutableshop.id }})</i> {{ mutableshop.name }}
        {{mutableshop.info}}
        {{mutableshop.address}}

        <director-row :director="mutableshop.director" :editMethod="editDirector" />

        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
            <input type="button" value="X" @click="del" />
         </span>
    </div>
</template>
<script>
    import {url} from "@/main"
    export default {
        components: {
          DirectorRow: () => import('./DirectorRow.vue')
        },
        props: ["shop", "editShop", "shops"],
        data() {
            return{
                mutableshop: this.shop,
                mutableshops: this.shops
            }
        },
        methods: {
            edit: function() {
                this.editShop(this.mutableshop);
            },
            del: function() {
                this.$http.delete(url + "/shop/" + this.mutableshop.id.toString()).then(result => {
                    if (result.ok) {
                        this.mutableshops.splice(this.mutableshops.indexOf(this.shop), 1)
                    }
                })
            },
            editDirector: function(director) {
                this.mutableshop.director = director;
            }
        }
    }
</script>