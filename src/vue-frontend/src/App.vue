<template>

  <PageLoader/>

  <Weather/>
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

</style>
