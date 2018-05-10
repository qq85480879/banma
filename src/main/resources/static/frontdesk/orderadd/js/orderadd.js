$(function () {
   $("#js-add").click(function () {
       alert("hello world");
       getParam();
   });


   function getParam() {
       var param = {
           province:$("#province3").val(),
           city:$("#city3").val(),
           district:$("#district3").val(),
           address:$("#js-address").val(),
           consignee:$("#js-consignee").val(),
           contact:$("#js-contact").val(),

       };
       var message = JSON.stringify(param);
       alert(message);
   }

});