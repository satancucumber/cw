<template>
    <div>
         <vue-good-table
                :columns="columns"
                :rows="products"
                class="table"
        >
            <template slot="table-row" slot-scope="props">
                  <span v-if="props.column.field == 'before'">
                    <b-button pill variant="primary" @click="editRow(props.row.id)">Изменить</b-button>
                    <b-button pill variant="primary" @click="deleteRow(props.row.id)">Удалить</b-button>
                  </span>
                  <span v-else>
                    {{props.formattedRow[props.column.field]}}
                  </span>
            </template>
        </vue-good-table>
        <b-form inline id="form">
              <label class="sr-only" for="inline-form-input-name">Name</label>

              <b-form-input
                  v-model="name"
                  id="inline-form-input-name"
                  class="mb-2 mr-sm-2 mb-sm-0"
                  placeholder="Название"
              ></b-form-input>

              <b-form-input
                  v-model="article"
                  id="inline-form-input-name"
                  class="mb-2 mr-sm-2 mb-sm-0"
                  placeholder="Артикул"
              ></b-form-input>

              <b-form-input
                  v-model="count"
                  id="inline-form-input-name"
                  class="mb-2 mr-sm-2 mb-sm-0"
                  placeholder="Количество"
              ></b-form-input>

              <b-form-input
                  v-model="price"
                  id="inline-form-input-name"
                  class="mb-2 mr-sm-2 mb-sm-0"
                  placeholder="Цена"
              ></b-form-input>

              <b-button pill variant="primary" @click="save">Сохранить</b-button>
        </b-form>
        <b-button pill variant="secondary" @click="add">Добавить</b-button>
        <b-button pill variant="secondary" @click="getJSON">Выгрузить в формате .JSON</b-button>
        <b-button pill variant="secondary" @click="generatePDF">Выгрузить в формате .PDF</b-button>
        <p></p>
        <b-form-file
            accept=".json"
            v-model="file"
            :state="Boolean(file)"
        ></b-form-file>
        <b-button pill variant="secondary" @click="addJSON">Звгрузить файл в формате .JSON</b-button>
        <b-alert>
              <p>{{ alertText }}</p>
        </b-alert>
    </div>
</template>

<script>
    import {url} from "@/main";
    import jsPDF from 'jspdf'
    import font from '@/kztimesnewroman-normal';
    export default {
      name: 'ProductsView',
      data() {
        return {
          file: null,
          id: null,
          name: '',
          article: '',
          count: null,
          price: null,
          alertText: "Ошибка!",

          columns: [
            {
              label: 'Before',
              field: 'before'
            },
            {
              label: 'ID',
              field: 'id'
            },
            {
              label: 'Название',
              field: 'name',
            },
            {
              label: 'Артикул',
              field: 'article',
            },
            {
              label: 'Количество',
              field: 'count',
              type: 'number',
            },
            {
              label: 'Стоимость',
              field: 'price',
              type: 'number',
            },
          ],
          products: [],
        }
      },
      created() {
          this.getData()
        },
        methods: {
          getData() {
              this.$http.get(url + "/product").then(response => {
                  const products = response && response.data ? response.data : []
                  this.products = products.map(r => {
                      return {
                          id: r.id,
                          name: r.name,
                          article: r.article,
                          count: r.count,
                          price: r.price
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
                  this.id = null;
                  this.name = '';
                  this.article = '';
                  this.count = '';
                  this.price = '';
            },
            save() {
                var product = {
                    id: this.id,
                    name: this.name,
                    article: this.article,
                    count: this.count,
                    price: this.price
                };
                if (this.id) {
                    this.$http.put(url + "/product/" + this.id.toString(), product).
                        then(()=>this.clean()).
                        then(()=>this.getData())

                } else {
                    this.$http.post(url + "/product", product).
                        then(()=>this.clean()).
                        then(()=>this.getData())
                }
            },
            add() {
              document.getElementById("form").style.display = "block";
            },
            editRow(id) {
                var index = this.getIndex(this.products, id);
                document.getElementById("form").style.display = "block";
                this.id = this.products[index].id;
                this.name = this.products[index].name;
                this.article = this.products[index].article;
                this.count = this.products[index].count;
                this.price = this.products[index].price;
            },
            deleteRow(id) {
                this.$http.delete(url + "/product/" + id.toString()).
                      then(()=>this.getData())
            },
          getJSON() {
                var fileDownload = require('js-file-download');
                fileDownload(JSON.stringify(this.products), 'products.json');
          },
          addJSON() {
            let reader = new FileReader()
            reader.readAsText(this.file)
            reader.onload = () => {
              let data = JSON.parse(reader.result) // [{},{}]
              this.$http.post(url + "/product/list", data).catch(() => {
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
                let text = "Продукты\n\n";
                for (let i = 0; i < this.products.length; i++) {
                  text +="ID: " +
                  this.products[i].id + "\nНазвание: " + this.products[i].name + "\nАртикул: " + this.products[i].article + "\nКоличество: " + this.products[i].count + "\nСтоимость: " + this.products[i].price + "\n\n"
                }
                pdf.text(text, 10, 10)
                pdf.save("products.pdf")
              }

        },
      };
</script>

<style scoped>
    #form {
        display: none;
    }
</style>