<template>
    <div>
        <vue-good-table
            :columns="columns"
            :rows="sellers"
            class="table"
        >
            <template slot="table-row" slot-scope="props">
                  <span v-if="props.column.field == 'before'">
                    <b-button variant="primary" @click="editRow(props.row.id)">Изменить</b-button>
                    <b-button variant="primary" @click="deleteRow(props.row.id)">Удалить</b-button>
                  </span>
                  <span v-else>
                    {{props.formattedRow[props.column.field]}}
                  </span>
            </template>
        </vue-good-table>
        <b-button variant="primary" @click="add">Добавить</b-button>
        <b-form inline id="form">
            <label class="sr-only" for="inline-form-input-name">Shop</label>
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
            <b-button pill variant="secondary" @click="getJSON">Выгрузить в формате .JSON</b-button>
            <b-button pill variant="secondary" @click="generatePDF">Выгрузить в формате .PDF</b-button>
            <p></p>
            <b-form-file
                accept=".json"
                v-model="file"
                :state="Boolean(file)"
            ></b-form-file>
            <b-button pill variant="secondary" @click="addJSON">Звгрузить файл в формате .JSON</b-button>
    </div>
</template>
<script>
    import {url} from "@/main";
    import jsPDF from 'jspdf'
    import font from '@/kztimesnewroman-normal';
    export default {
          name: 'SellersView',
          data() {
            return {
                id: null,
                name: '',
                lastname: '',
                fathername: '',
                birth: '',
                phone: '',
                email: '',
                columns: [
                    {
                      label: 'Before',
                      field: 'before'
                    },
                    {
                      label: 'ID',
                      field: 'id',
                      sortable: true,
                    },
                    {
                      label: 'Фамилия',
                      field: 'lastname',
                      sortable: true,
                    },
                    {
                      label: 'Имя',
                      field: 'name',
                      sortable: true,
                    },
                    {
                      label: 'Отчество',
                      field: 'fathername',
                      sortable: true,
                    },
                    {
                      label: 'Дата рождения',
                      field: 'birth',
                      sortable: true,
                    },
                    {
                      label: 'Тел.',
                      field: 'phone',
                      sortable: true,
                    },
                    {
                      label: 'Эл. почта',
                      field: 'email',
                      sortable: true,
                    }
                  ],
                  sellers: []
            }
          },
          created() {
                this.getData()
          },
          methods: {
              getData() {
                this.$http.get(url + "/seller").then(response => {
                    const sellers = response && response.data ? response.data : []
                    this.sellers = sellers.map(r => {
                        return {
                            id: r.id,
                            name: r.name,
                            lastname: r.lastname,
                            fathername: r.fathername,
                            birth: r.birth,
                            phone: r.phone,
                            email: r.email,
                        }
                    })
                })
              },
              getIndex(list, id) {
                  for (var i = 0; i < list.length; i++ ) {
                      if (list[i].id === id) {
                          return i;
                      }
                  }
              },
              clean() {
                    document.getElementById("form").style.display = "none";
                    this.name = '';
                    this.id = null;
                    this.lastname = '';
                    this.fathername = '';
                    this.birth = '';
                    this.phone = '';
                    this.email = '';
              },
              save() {
                  var seller = {
                      name: this.name,
                      lastname: this.lastname,
                      fathername: this.fathername,
                      birth: this.birth,
                      email: this.email,
                      phone: this.phone
                  };
                  if (this.id) {
                      this.$http.put(url + "/seller/" + this.id.toString(), seller).
                          then(()=>this.clean()).
                          then(()=>this.getData())

                  } else {
                      this.$http.post(url + "/seller", seller).
                          then(()=>this.clean()).
                          then(()=>this.getData())
                  }
              },
              add() {
                document.getElementById("form").style.display = "block";
              },
              editRow(id) {
                  var index = this.getIndex(this.sellers, id);
                  document.getElementById("form").style.display = "block";
                  this.name = this.sellers[index].name;
                  this.id = this.sellers[index].id;
                  this.lastname = this.sellers[index].lastname;
                  this.fathername = this.sellers[index].fathername;
                  this.birth = this.sellers[index].birth;
                  this.phone = this.sellers[index].phone;
                  this.email = this.sellers[index].email;
              },
              deleteRow(id) {
                  this.$http.delete(url + "/seller/" + id.toString()).
                        then(()=>this.getData())
              },
              getJSON() {
                  var fileDownload = require('js-file-download');
                  fileDownload(JSON.stringify(this.sellers), 'sellers.json');
              },
            addJSON() {
              let reader = new FileReader()
              reader.readAsText(this.file)
              reader.onload = () => {
                let data = JSON.parse(reader.result) // [{},{}]
                this.$http.post(url + "/sellers/list", data).catch(() => {
                  this.alertText = "Ошибка!"
                }).then(() => this.getData())
              }
            },
            generatePDF() {
                  const pdf = new jsPDF()
                  console.log(pdf.getFontList())
                  const myFont = font;

            // add the font to jsPDF
                  pdf.addFileToVFS("MyFont.ttf", myFont);
                  pdf.addFont("MyFont.ttf", "MyFont", "normal");
                  pdf.setFont("MyFont");
                  let text = "Продавцы\n\n";
                  for (let i = 0; i < this.sellers.length; i++) {
                    text +="ID: " +
                    this.sellers[i].id +
                        "\nФИО: " + this.sellers[i].lastname + " " + this.sellers[i].name + " " + this.sellers[i].fathername +
                        "\nДата рождения: " + this.sellers[i].birth +
                        "\nНомер телефона: " + this.sellers[i].phone +
                        "\nАдрес эл.почты: " + this.sellers[i].email + "\n\n"
                  }
                  pdf.text(text, 10, 10)
                  pdf.save("sellers.pdf")
                }
          }
    }
</script>
<style scoped>
    #form {
        display: none;
    }
</style>