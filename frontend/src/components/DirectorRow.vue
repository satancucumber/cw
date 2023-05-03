<template>
    <div>
        {{ mutabledirector.lastname }} {{ mutabledirector.name }} {{ mutabledirector.fathername }}
        {{ mutabledirector.birth }}
        {{ mutabledirector.phone }}
        {{ mutabledirector.email }}

        <span style="position: absolute; right: 0">
            <input type="button" value="Edit" @click="edit" />
        </span>
    </div>
</template>
<script>
    export default {
        props: ["director", "editMethod"],
        data() {
            return {
                mutabledirector: this.director
            }
        },
        methods: {
            edit() {
                this.editMethod(this.director)
            }
        }

    }
</script>