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
  }
}
</script>

<style>

</style>
