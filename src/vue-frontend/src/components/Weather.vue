<template>

  <div class="container" style="width:55%;">
    <h3>General Information</h3>
    <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's
      standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a
      type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,
      remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing
      Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions
      of Lorem Ipsum.</p>
    <h4>Location Based Information</h4>
  </div>

  <section class="dropdown-wrapper">

    <div @click="isVisible = !isVisible" class="selected-item">
      <span v-if="selectedItem"> {{ selectedItem }}</span>  <!-- selectedItem.name -->
      <span v-else>Select City</span>  <!-- selectedItem.name -->
      <svg :class="isVisible ? 'dropdown' : ''"
           class="drop-down-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24" width="24" height="24">
        <path fill="none" d="M0 0h24v24H0z"/>
        <path d="M12 10.828l-4.95 4.95-1.414-1.414L12 8l6.364 6.364-1.414 1.414z"/>
      </svg>
    </div>

    <div :class="isVisible ? 'visible' : 'invisible'" class="dropdown-popover">
      <input v-model="searchQuery" type="text" placeholder="Pick for city"/>
      <span v-if="filteredUser.length ===0">No data found!</span>
      <div class="options">
        <ul>
          <!--          <li v-for="(user,index) in userArray" :key="`user-${index}`"> {{ user.name }}</li> FOR LISTING ELEMENTS FRom API REQUEST -->
          <!--          <li v-for="city in cityArray" v-bind:key ="city.id"> {{ city }}</li>-->
          <li @click="selectItem(city)" v-for="city in filteredUser" v-bind:key="city.id"> {{ city }}</li>
          <!--        HEREEEEEEEEEEEEEEEEEEEEEE-->
        </ul>
      </div>
    </div>
  </section>

  <!--<section>-->
  <!--    <div class="switch">-->
  <!--&lt;!&ndash;      DO YOU REMEMBER YOUTUBE VIDEO OF JS PROJECTS? USE IT TO DO SWITCH&ndash;&gt;-->
  <!--      <div class="button rounded-5 shadow p-4">-->
  <!--        <div class="text-uppercase">Daytime</div>-->
  <!--      </div>-->

  <!--      <div class="button rounded-5 shadow p-4" >-->
  <!--        <div class="text-uppercase">Night</div>-->
  <!--      </div>-->
  <!--    </div>-->
  <!--</section>-->

  <!--    <div class="menu-item">-->
  <!--      <a href="#">-->
  <!--        Tartu-->
  <!--      </a>-->
  <!--    </div>-->


  <!--      <label for="tartu">Tartu</label>-->
  <!--      <input type="radio" value="tartu" id="tartu" v-model="city" name="city">-->

  <!--      <label for="talinn">Talinn</label>-->
  <!--      <input type="radio" value="talinn" id="talinn" v-model="city" name="city">-->

  <!--      <label for="tartu">Tartu</label>-->
  <!--      <input type="radio" value="tartu1" id="tartu1" v-model="city" name="city">-->

  <!--      <label for="tartu">Tartu</label>-->
  <!--      <input type="radio" value="tartu2" id="tartu2" v-model="city" name="city">-->


  <!--    <table class="table table-striped">-->
  <!--      <thead>-->
  <!--      <th> Datetime</th>-->
  <!--      <th> Message</th>-->
  <!--      <th> City</th>-->
  <!--      <th> Condition</th>-->
  <!--      <th> Max temp</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
  <!--      <th> Min temp</th>  &lt;!&ndash; (if exist) &ndash;&gt;-->
  <!--      <th> Wind Direction</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
  <!--      <th> Min speed</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
  <!--      <th> Max speed</th> &lt;!&ndash; (if exist) &ndash;&gt;-->
  <!--      </thead>-->
  <!--      <tbody>-->
  <!--      <tr v-for="weather in weatherInfo" v-bind:key="weather.id">-->
  <!--        <td> {{ weather.id }}</td>-->
  <!--        <td> {{ weather.dateTime }}</td>-->
  <!--        <td> {{ weather.message }}</td>-->
  <!--        <td> {{ weather.city }}</td>-->
  <!--        <td> {{ weather.condition }}</td>-->

  <!--      </tr>-->

  <!--      </tbody>-->
  <!--    </table>-->

  <!--  <h4>City : {{city}}</h4>-->

</template>

<script>

export default {
  name: 'WeatherInformation',
  data() {
    return {
      searchQuery: '',
      selectedItem: null,
      isVisible: false,
      weatherInfo: [],
      cityArray: [],
      city: null
    }
  },
  computed: {
    filteredUser() {
      const query = this.searchQuery.toLowerCase()
      if (this.searchQuery === "") {
        return this.cityArray;
      } else {
        return this.cityArray.filter((city) => {

          return city.toLowerCase().includes(query);
          // WHEN U USE MAP -> return Object.values(city).some((word => String(word).toLowerCase().includes(query)));
        })
      }
    },
  },
  mounted() {

  },
  methods: {
    selectItem(city) {
      this.selectedItem = city;
      this.isVisible = false;

    },
    getWeather() {
      const weatherAPI = 'http://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng';
      const proxyURL = `https://thingproxy.freeboard.io/fetch/${weatherAPI}`

      fetch(proxyURL).then(response => response.text())
          // .then ((response) => console.log(response));
          .then(data => {
            // console.log(data);  //string
            let parser = new DOMParser();
            let xml = parser.parseFromString(data, "text/xml");
            let forecast = xml.getElementsByTagName('forecast');
            //let info = new Array();
            let singleDay = new Map();


            // document.getElementById('output').textContent = data;
            // console.log(xml);
            // console.log("END OF DOC \n\n\n");

            // console.log(forecast[0]);
            // console.log("END OF DOC \n\n\n");

            // console.log(forecast[0].getAttribute("date"));
            // console.log("END OF DOC \n\n\n");

            for (let itr = 0; itr < forecast.length; itr++) {
              let date = forecast[itr].getAttribute("date");
              let night = new Map();
              singleDay.set('date', date);
              let nightObj = forecast[itr].getElementsByTagName('night');


              let phenomenon = nightObj[0].getElementsByTagName("phenomenon")[0].innerHTML;
              let generalMin = nightObj[0].getElementsByTagName("tempmin")[0].innerHTML;
              let generalMax = nightObj[0].getElementsByTagName("tempmax")[0].innerHTML;
              let generalInfo = nightObj[0].getElementsByTagName("text")[0].innerHTML;
              let sea = nightObj[0].getElementsByTagName("sea")[0]?.innerHTML;
              let peipsi = nightObj[0].getElementsByTagName("peipsi")[0]?.innerHTML;
              sea = sea ? "" : "";
              peipsi = peipsi ? "" : "";

              // console.log(nightObj[itr1]);
              // console.log(`phenomenon: ${phenomenon}`);
              // console.log(`tempmin: ${generalMin}`);
              // console.log(`tempmax: ${generalMax}`);
              // console.log(`text: ${generalInfo}`);

              night.set('phenomenon', phenomenon);
              night.set('tempmin', generalMin);
              night.set('tempmax', generalMax);
              night.set('text', generalInfo);
              night.set('sea', sea);
              night.set('peipsi', peipsi);
              console.log(night);


            }
            //


            //CHANGE IT to get from XML FILE
            this.cityArray = ["Tartu", "Tallinn", "Dublin", "Armatur", "Beton", "Sement", "Sikim"]
          });

    }
  },
  created() {
    this.getWeather()
  }
}
</script>


<style>
.button {
  cursor: pointer;
  font-weight: bold;
  font-size: x-large;
  margin-right: 1rem;
  margin-bottom: 1rem;
  width: 12rem;
  background-color: #15d6A0;
  justify-content: center;
  display: inline-block;
}

.button:hover {
  background-color: yellow;
}

.switch {
  justify-content: center;
  display: inline-block;
  width: fit-content;
  background-color: #FFC425;
}

.button:hover {
  border-style: groove;
  border-color: black;

}
</style>

<style scoped lang="scss">

.dropdown-wrapper {
  max-width: 12rem;
  position: relative;
  margin: 0 auto;


  .selected-item {
    height: 2rem;
    border: 2px solid lightgray;
    border-radius: 4px;
    padding: 5px 10px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    font-size: 18px;
    font-weight: 400;

    .drop-down-icon {
      transform: rotate(0deg);
      transition: all 0.5s ease;

      &.dropdown {
        transform: rotate(180deg);
      }
    }
  }

  .dropdown-popover {
    position: absolute;
    border: 2px solid lightgray;
    top: 46px;
    left: 0;
    right: 0;
    background-color: #15d6A0;
    max-width: 100%;
    padding: 10px;

    visibility: hidden;
    transition: all 0.5s ease-in-out;
    max-height: 0px;
    overflow: hidden;

    &.visible {
      visibility: visible;
      max-height: 450px;
    }

    input {
      width: 90%;
      border: 2px solid lightgray;
      font-size: 16px;
      padding-left: 8px;
    }

    .options {
      width: 100%;

      ul {
        list-style: none;
        text-align: left;
        padding-left: 8px;
        max-height: 180px;
        overflow-y: scroll;
        overflow-x: hidden;
        border: 1px solid lightgray;

        li {
          width: 100%;
          border-bottom: 1px solid lightgray;
          padding: 10px;
          background-color: #f1f1f1;
          cursor: pointer;
          font-size: 16px;

          &:hover {
            background: #70878a;
            color: #fff
          }
        }
      }
    }
  }
}
</style>
