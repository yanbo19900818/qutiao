$(function(){
	function dateRegion(startDate, endDate){
		startDate.datepicker({
			changeMonth: true,
			dateFormat: 'yy-mm-dd',
			onSelect: function(selectedDate){
				endDate.datepicker('option', 'minDate', selectedDate);
			}
		});
		endDate.datepicker({
			changeMonth: true,
			dateFormat: 'yy-mm-dd',
			onSelect: function(selectedDate){
				startDate.datepicker('option', 'maxDate', selectedDate);
			}
		})
	}
	
	dateRegion($('#FromDate'), $('#ToDate'));
});

