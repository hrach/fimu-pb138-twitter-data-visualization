$(document).ready(function(){
   var dates = $("#from, #to").datepicker({
       defaultDate: "-2w",
       dateFormat: "yy-mm-dd",
       changeMonth: true,
       numberOfMonths: 3,
       onSelect: function(selectedDate) {
           var option = this.id == "from" ? "minDate" : "maxDate",
           instance = $(this).data("datepicker"),
           date = $.datepicker.parseDate(instance.settings.dateFormat || $.datepicker._defaults.dateFormat,selectedDate, instance.settings);
           dates.not(this).datepicker("option", option, date);
       }
   });
   
   $("form#dateForm").submit(function(){
      if($("#from").val()=="" || $("#to").val()=="") {
          alert("Časový interval nebyl správně vyplněn!");
          return false;
      }
   });

   function checkTags() {
      var $b = $('input[type=checkbox]');
      if($b.filter(':checked').length >= 10) {
          $b.filter(':not(:checked)').attr('disabled','disabled');
      } else {
          $b.removeAttr('disabled');
      }
   }

   checkTags();

   $(".htag").click(function() {
      checkTags();
   });
   
   $("form#trendForm").submit(function(){
      var $b = $('input[type=checkbox]');
      if($b.filter(':checked').length == 0) {
          alert("Pro zobrazení dat musíte vybrat alespoň 1 trend.");
          return false;
      }
   });
});