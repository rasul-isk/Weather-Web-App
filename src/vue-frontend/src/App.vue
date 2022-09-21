<template>

  <PageLoader/>
  <div id="app" class="min-vh-100 bg-light" style="padding-top: 60px">
    <h1 style="color: #15D6A0;">Weather in Estonia</h1>
    <hr class="center-line">
    <Weather/>
  </div>
</template>

<script>
import Weather from "@/components/Weather";
import PageLoader from "@/components/PageLoader";

export default {
  name: 'App',
  components: {
    PageLoader,
    Weather
  },
  // mounted: function () { }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #021510;
  margin-top: 0px;
}

.center-line {
  margin-left: auto;
  margin-right: auto;
  width: 60%;

}
</style>
