/**
 * 
 */

let formdata ={};
/* let formtotal ={}; */
const allheads =["PASSENGER","OTHER COACHING","ORIGINATING GOODS","OTHER GOODS","SUNDRY","PASSENGER Units"];
let isBottomFormdisabled = true;

document.addEventListener('keydown', function (event) {

    // Check Enter key
    if (event.key !== 'Enter') return;

    // Only act for inputs inside detail table
    if (!event.target.matches('#tbl_budget_body input')) return;

    event.preventDefault();      // stop form submit
    event.stopPropagation();     // stop bubbling

    const inputs = Array.from(
        document.querySelectorAll('#tbl_budget_body input:not([readonly])')
    );
    const index = inputs.indexOf(event.target);
    const next = inputs[index + 1];

    if (next) {
        next.focus();
    }
});

/*document.addEventListener('DOMContentLoaded', function () {
    // Select all input fields inside your table
    const inputs = document.querySelectorAll('#tbl_budget_body input');

    inputs.forEach((input, index) => {
        input.addEventListener('keydown', function (event) {
            if (event.key === 'Enter') {
                event.preventDefault(); // Stop form submission
                
                // Move to next input if it exists
                const next = inputs[index + 1];
                if (next) {
                    next.focus();
                }
            }
        });
    });
});*/


/*$(function(){              // shorthand for $(document).ready(...)
  
	$('#top-fieldset').on('change input', 'input, select', function() {
		
		if(!isBottomFormdisabled){
			
			isBottomFormdisabled = !isBottomFormdisabled;
			resetBottomForm();
			$("#formFields").prop("disabled",true);
			$("#budget-no").trigger("change");
		}			
	    
	});
	
});
*/


function checkValue(ele){
	
	//console.log("checkValue called");
	/*resetBottomForm();*/
	let year = ele.value;	
	if(year !=null && year.length == 4){
		
		let nextyear = +year.slice(-2) + 1;
		//console.log("nextyear = " +nextyear);
		$("#txt-financial-year-2").val(nextyear);				
		let fy = year+"-"+nextyear;
		$("#financialYear").val(fy);
		/*getMaxBudgetNumber();*/
	}
	else{
		
		$("#financialYear").val("");
		/*$("#formFields").prop('disabled', true);*/	
		$("#txt-financial-year-1").val("");
		$("#txt-financial-year-2").val("");					
		alert("Enter valid year.");
	}	
	
	$("#division").val("-1").trigger("change");

}

function divisionChanged(ele){
	
	//console.log("divisionChanged called");
	let div = $(ele).val();
	if(div !=null && div != "-1"){		
		getMaxBudgetNumber();
		
	}else{
		
		$('#budget-no option[value!="-1"]').remove();
		$('#budget-no').prop("disabled",true);
	}
	
	$("#budget-no").trigger("change");
}

function budgetNoChanged(ele){
	
	//console.log("budgetNoChanged called");
	/*resetBottomForm();*/
	if(!isBottomFormdisabled){
	
		isBottomFormdisabled = !isBottomFormdisabled;		
		reloadBottomPart();	
	}else{
		loadMonthValueFromDB();
	}
	
}

function loadMonthValueFromDB(){
	
	//console.log("loadMonthValueFromDB called");
	
		let budgetno = $("#budget-no").val();
		if(budgetno == '1' || budgetno == '-1'){
			$("#for-month").val("04").trigger('change');
			$("#for-month").prop('disabled', true);
		}
		else{
			
			getForMonthForBudgetNo();
			/*$("#for-month").prop('disabled', false);*/
		}	
}

function reloadBottomPart(){
	
	//console.log("reloadBottomPart called");
	
	let formData = $("#top-form").serialize();
	$.ajax({
	    url: "/budget/getbudgetdata?mode=blank",
		type: "POST",
		data: formData,
		success: function(response) {

			$("#detail-container").html(response);
			disableBottomForm(true);
			loadMonthValueFromDB();

		},
		error: function() {
			disableBottomForm(true);
			alert("Something went wrong!");
		}
   }); 
}

function monthChanged(ele){
	
	//console.log("monthChanged called");	
	/*resetBottomForm();*/
	/*resetAllReadonlyTextfields();*/

	/*SETTING HIDDEN FOR MONTH TEXTFIELD FOR VALUE TO PASS TO THE CONTROLLER*/
	let monthval = $("#for-month").val();
	$("#input-formonth").val(monthval);
/*	if(monthval === "-1" || monthval === "04" || monthval === "05")
		return;*/
	
	makeTextReadonlyForMonth();
	
	/*$("#budget-no").trigger('change');*/
}

function resetAllReadonlyTextfields(){

	//console.log("resetAllReadonlyTextfields called");
	let tempfield_name = null;
	let inputfield = null;
	allheads.forEach((head, index) => {
		
			for(let i=1;i<=12;i++){
				
				if(head !== "PASSENGER Units"){
					
					tempfield_name = "input_" + head + "_" + "1" + "_" + i;
					inputfield = document.getElementsByName(tempfield_name);
					if (inputfield != null)
						$(inputfield).prop('readonly', false);
						

					tempfield_name = "input_" + head + "_" + "2" + "_" + i;
					inputfield = document.getElementsByName(tempfield_name);
					if (inputfield != null)					
						$(inputfield).prop('readonly', false);

				}else{
					
					tempfield_name = "input_" + head + "_" + i;
					inputfield = document.getElementsByName(tempfield_name);
					if (inputfield != null)
						$(inputfield).prop('readonly', false);
				}
				
			}
			
		});
}

function makeTextReadonlyForMonth(){

	//console.log("inside makeTextReadonlyForMonth");
	/*changeHeaderTitle("-1");*/
	let budgetno =  $("#budget-no").val();
	if(budgetno == null || budgetno == ''  || budgetno == '-1')
		return;
	
						
	let monthval = $("#for-month").val();
	
	//console.log("monthval = " + monthval);

	
	if(monthval == null || monthval.length == 0)
		return;

	
	const monthCodes = ["04","05","06","07","08","09","10","11","12","01","02","03"];
	let monthindex = monthCodes.indexOf(monthval);
	if(monthindex != -1){
		
		changeHeaderTitle(monthval);
		monthindex -=2;		
		monthCodes.forEach((month,index)=>{
						
			document.querySelectorAll("input[data-col='" + month + "']")
				.forEach(inp => {
		
					if(inp.dataset.row =="TEARN_UNIT" || inp.dataset.row == "TEARN_AMT"){
						$(inp).prop('readonly', true);
						$(inp).prop('disabled', true);
					}else{
						
						if (index <= monthindex) {
							//console.log("index =" +index);
							/*$(inp).val("1");*/
							$(inp).prop('readonly', true);
							$(inp).prop('disabled', true);
						} else {

							/*$(inp).val("12");*/
							$(inp).prop('readonly', false);
							$(inp).prop('disabled', false);
						}

					}
				});

		});
			
	}	
}

function changeHeaderTitle(monthcode){
	
	const monthCodes = ["04","05","06","07","08","09","10","11","12","01","02","03"];
	const monthNames = ["Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec","Jan","Feb","Mar"];
	
	//First reset all header
	monthCodes.forEach((month,index)=>{
		
		const headname ="#thead-"+month;
		$(headname).text(monthNames[index]);
			
	});
	
	/*Change Consolidated month header*/
	if(monthcode !== "-1"){
		let monthindex = monthCodes.indexOf(monthcode);
		const consolidatedMonth = monthNames[monthindex -1];
		const headname ="#thead-"+monthCodes[monthindex -1]; 
		$(headname).html("Cummulative Upto " +consolidatedMonth);	
	}
}

function getForMonthForBudgetNo(){

	//console.log("getForMonthForBudgetNo called");
	let fy = $("#txt-financial-year-1").val() + "-" + $("#txt-financial-year-2").val();
	let div = $("#division").val();
	let budgetno = $("#budget-no").val();
	
	if (fy.length == 7 && budgetno != null && budgetno.length >0) {
		
		var jsondata = {
			financialyear: fy,
			budgetnumber: budgetno,
			division:div		
		};

		$.ajax({
			url: "/budget/getForMonthForBudgetnumber",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(jsondata),
			success: function(response) {

				if (response.status === "success") {
					
					if(response.formonth === "-1"){
						$("#for-month").val("-1");
						$("#for-month").prop('disabled', false);	
						$("#input-formonth").val("");						
					}					
					else{
						$("#for-month").val(response.formonth);
						$("#input-formonth").val(response.formonth);
						$("#for-month").prop('disabled', true);
					}
						
					/*resetAllReadonlyTextfields();*/
					makeTextReadonlyForMonth();
					
				} else {

					$("#for-month").prop('disabled', true);
					$("#input-formonth").val("");
					//console.log("Some error occured.");
				}
			},
			error: function() {
				$("#for-month").prop('disabled', true);
				$("#input-formonth").val("");
				alert("Something went wrong!");
			}
		});

		
	}else{
		//console.log("Invalid data.");
	}
}

function getMaxBudgetNumber(){
	
	let fy = $("#txt-financial-year-1").val() + "-" + $("#txt-financial-year-2").val();
	let div = $("#division").val();
	if (fy.length == 7 && div !=null && div != "-1") {
		$.ajax({
			url: "/budget/getMaxBudgetnumber",
			type: "POST",
			data: {financialyear: fy, division:div},
			success: function(response) {

				if (response.status === "success") {

					$("#budget-no").prop("disabled", false);
					$("#division").prop("disabled", false);					
					resetBudgetnoList(response.maxbudgetno);					
					
				} else {

					//console.log("Some error occured.");
				}
			},
			error: function() {
				alert("Something went wrong!");
			}
		});

	} else {

		//console.log("Invalid financial Year or Division")
	}
}

function resetBudgetnoList(maxbudgetno){
	
	$('#budget-no option[value!="-1"]').remove();
	for(let i=0;i<=maxbudgetno;i++){
		
		let option = '<option value="'+(i+1)+'">'+(i+1)+'</option>';
		$('#budget-no').append(option);		
	}
}

function showBudgetDetailClicked(ele){
	
	$("#status_span").text("");
	/*resetBottomForm();*/
	let fy1 = $("#txt-financial-year-1").val();
	let fy2 = $("#txt-financial-year-2").val();
	let budgetno = $("#budget-no").val();
	let formonth = $("#for-month").val();
	let division = $("#division").val();
	
	if(fy1 != null && fy1.length != 4){
		alert("Enter valid financial year")
	}else if(fy2 != null && fy2.length != 2){
		alert("Enter valid financial year")
	}else if(division == "-1"){
		alert("Select division.")
	}else if(budgetno == "-1"){
		alert("Select budget number.")
	}else if(formonth == "-1"){
		alert("Select for month.")
	}else{
				
		let formData = $("#top-form").serialize();
		
		$.ajax({
           url: "/budget/getbudgetdata?mode=load",
           type: "POST",
           data: formData,
           success: function (response) {
			
			$("#detail-container").html(response);
			disableBottomForm(false);
			makeTextReadonlyForMonth();
           	
           },
           error: function () {
				disableBottomForm(true);
               alert("Something went wrong!");
           }
       }); 
		
	}	
}

function disableBottomForm(disable){
	
	isBottomFormdisabled = disable;
	$("#budget-detail-fieldset").prop("disabled",disable);
}


function submitClicked(){

	$("#status_span").text("");
	let fy1 = $("#txt-financial-year-1").val();
	let fy2 = $("#txt-financial-year-2").val();
	let budgetno = $("#budget-no").val();
	let formonth = $("#for-month").val();
	let division = $("#division").val();
	
	if(fy1 != null && fy1.length != 4){
		alert("Enter valid financial year")
	}else if(fy2 != null && fy2.length != 2){
		alert("Enter valid financial year")
	}else if(budgetno == "-1"){
		alert("Select budget number.")
	}else if(formonth == "-1"){
		alert("Select for month.")
	}else if(division == "-1"){
		alert("Select division.")
	}else{
			
		let formData = $("#top-form").serialize();	
		
		$.ajax({			
			url: "/budget/savebudgetdata",
			type: "POST",			
			data: formData,
			success: function(response) {
				//console.log("response =" + response);
				if (response.status === "success") {

					$("#status_span").text("Data saved successfully!");
					$("#status_span").removeClass('error').addClass('success');

				} else {

					$("#status_span").text("Some error occured.");
					$("#status_span").removeClass('success').addClass('error');
				}

				resetAllFormAndData();
			},
			error: function() {
				alert("Something went wrong!");
				resetAllFormAndData();
			}
		}); 	
		
	}	
	
}

function fyValueEdited(ele){
	
	$("#financialYear").val("");
}


function captureFormValue(ele){
	
	if ($(ele).is('[readonly]')) {
		//console.log("readonly")
		return;
	}
	
	
	let field_name = ele.name;
	
	/*let monthindex = "";
	let parts = field_name.split("_");
	 if(parts.length == 4)
		 monthindex = parts[3];
	 else if(parts.length == 3)
		 monthindex = parts[2];
	 else
		 monthindex= "";
		 
	if (monthindex.lenght == 0 || monthindex == "13" || field_name.includes("TOTAL EARNING")) 
		return;*/
	
	
	let field_value = ele.value.trim();			
	let newvalue = (field_value !=null && field_value.length > 0)? (+field_value):0;
	
	if(field_value !=null && field_value.length > 0){
		
		
		if (!formdata[field_name]) {
		   formdata[field_name] = {};   
		   
		   formdata[field_name]["recordid"] = 0;
		   formdata[field_name]["orgval"] = "";
		   formdata[field_name]["isedited"] = 1;                			   
		   formdata[field_name]["newval"] = newvalue;     
		}else{
			
			let recid = formdata[field_name]["recordid"];
			if(recid == 0){
				
				formdata[field_name]["isedited"] = 1;                			   
				formdata[field_name]["newval"] = newvalue;	
				 
			}else{
				
				let oldval = +formdata[field_name]["orgval"];
				if(oldval === newvalue){
					formdata[field_name]["isedited"] = 0;
				}					
				else{
					formdata[field_name]["isedited"] = 1;
					formdata[field_name]["newval"] = newvalue;
				}
				
			}
			  
		}
		
	}else if(field_value !=null && field_value.length == 0){
		
		if (formdata[field_name]) {
			
			if(formdata[field_name]["recordid"] !=0){
				
				 formdata[field_name]["isedited"] = 1;                			   
				 formdata[field_name]["newval"] = newvalue;
				 
			}else{
				
				delete formdata[field_name];
			}			   
		}
	}	
	
	
	dynamicallyUpdateTotalValue(field_name);
	
	
	/* //console.log("edited data = " +JSON.stringify(formdata)); */	
}

function dynamicallyUpdateTotalValue(field_name){
	
	let serachkey = "";
	let parts = field_name.split("_");
	 if(parts.length == 4)
		 serachkey = parts[1]+"_"+parts[2];
	 else if(parts.length == 3)
		 serachkey = parts[1];
	 else
		 return;	 
	
	 //Construct total field name
	const lastIndex = parts.length - 1;
	parts[lastIndex] = "13";
	let totFieldName = parts.join("_"); 
	
		
	const filtered_data = Object.fromEntries(Object.entries(formdata).filter(([key, value]) => key.includes(serachkey)));
	
	let totlval = 0.0; 
	for (const [fieldName, fieldData] of Object.entries(filtered_data)) {
		
		totlval += fieldData["newval"];	    
	}
	
	let totl_inputfiield =  document.getElementsByName(totFieldName);
	$(totl_inputfiield).val(totlval!=0?totlval:"");  
	
	   
	//Vertical total upadte
	parts = field_name.split("_");
	if(parts.length == 3)
		return;
	
	totlval = 0.0;
	let tempfield_name =""; 
	allheads.forEach((head, index) => {
		tempfield_name = "input_"+head+"_"+parts[2]+"_"+parts[3];
		if(formdata[tempfield_name]){
			totlval += +formdata[tempfield_name]["newval"];
		}
	});
	
	totFieldName = "input_TOTAL EARNING"+"_"+parts[2]+"_"+parts[3];
	totl_inputfiield =  document.getElementsByName(totFieldName);
	if(totl_inputfiield != null)
		$(totl_inputfiield).val(totlval!=0?totlval:"");  
	
	 if(parts.length == 4)
		updateGrandTotal(parts[2]);
}


function updateTotalValues(){
	
	let filtered_data = null;
	let totlval = 0.0; 
	let totFieldName = ""; 
	let searchkey = "";
	let totl_inputfiield = null;
	allheads.forEach((head, index) => {
		
		//set total for Units
		if(head !="PASSENGER Units")
			searchkey = head+"_"+"1";
		else
			searchkey = head;
		totFieldName = "input_"+searchkey+"_"+"13";
		
		filtered_data = Object.fromEntries(Object.entries(formdata).filter(([key, value]) => key.includes(searchkey)));
				
		totlval = 0.0;
		for (const [fieldName, fieldData] of Object.entries(filtered_data)) {
			
			totlval += +fieldData["newval"];	    
		}
		
		/* //console.log("totFieldName = "+totFieldName +"value = "+totlval); */
		totl_inputfiield =  document.getElementsByName(totFieldName);
		if(totl_inputfiield !=null)
			$(totl_inputfiield).val(totlval!=0?totlval:"");  
		
		
		if(head !="PASSENGER Units"){
			
			//set total for Amounts
			searchkey = head+"_"+"2";
			totFieldName = "input_"+searchkey+"_"+"13";
			filtered_data = Object.fromEntries(Object.entries(formdata).filter(([key, value]) => key.includes(searchkey)));
					
			totlval = 0.0;
			for (const [fieldName, fieldData] of Object.entries(filtered_data)) {
				
				totlval += +fieldData["newval"];	    
			}
					
			/* //console.log("totFieldName = "+totFieldName +"value = "+totlval); */
			totl_inputfiield =  document.getElementsByName(totFieldName);
			if(totl_inputfiield != null)
				$(totl_inputfiield).val(totlval!=0?totlval:"");  

		}		
		
	});
	
	
	//Vertical total upadte
	let totl_unit_val = 0.0;
	let totl_amount_val = 0.0;
	let temp_unit_field_name = "";
	let temp_amt_field_name = "";
	for(let i=1; i<=12; i++){
		
		totl_unit_val = 0.0;
		totl_amount_val = 0.0;
		allheads.forEach((head, index) => {						
			temp_unit_field_name = "input_"+head+"_"+"1"+"_"+i;
			temp_amt_field_name = "input_"+head+"_"+"2"+"_"+i;
			
			if(formdata[temp_unit_field_name]){
				totl_unit_val += +formdata[temp_unit_field_name]["newval"];
			}
			
			if(formdata[temp_amt_field_name]){
				totl_amount_val += +formdata[temp_amt_field_name]["newval"];
			}								
		});
		
		
		//update total for unit
		totFieldName = "input_TOTAL EARNING"+"_"+"1"+"_"+i;
		/* //console.log("Vertical toal unit field name ="+totFieldName + "& value = "+totl_unit_val); */
		totl_inputfiield =  document.getElementsByName(totFieldName);
		if(totl_inputfiield != null)
			$(totl_inputfiield).val(totl_unit_val!=0?totl_unit_val:"");
		
		totFieldName = "input_TOTAL EARNING"+"_"+"2"+"_"+i;
		/* //console.log("Vertical toal unit amount name ="+totFieldName + "& value = "+totl_amount_val); */
		totl_inputfiield =  document.getElementsByName(totFieldName);
		if(totl_inputfiield != null)
			$(totl_inputfiield).val(totl_amount_val!=0?totl_amount_val:"");
	}		
	
	updateGrandTotal("-1");
	
}

function updateGrandTotal(subhead){
	
	//update grand total
	//if subhead = -1 then update both unit and amount
	let totl_unit_val = 0.0;
	let totl_amount_val = 0.0;
	let temp_unit_field_name = "";
	let temp_amt_field_name = "";
	let totFieldName = "";
	let totl_inputfiield = null;
	allheads.forEach((head, index) => {		

		if(head !== "PASSENGER Units"){
			
			if((subhead == "-1" || subhead == "1")){
				temp_unit_field_name = "input_"+head+"_"+"1"+"_"+"13";
				let tot_unit_field =  document.getElementsByName(temp_unit_field_name);
				if(tot_unit_field != null){
					
					let val1 = $(tot_unit_field).val();
					if(val1.length >0)				
						totl_unit_val += +val1;  
				}
			}
			
			if((subhead == "-1" || subhead == "2")){
				
				temp_amt_field_name = "input_"+head+"_"+"2"+"_"+"13";		
				let tot__amt_field =  document.getElementsByName(temp_amt_field_name);
				if(tot__amt_field != null){
					
					let val2 = $(tot__amt_field).val();
					if(val2.length >0)
						totl_amount_val += +val2;  
				}
			}	
		}		
					
	});
	
	
	
	if((subhead == "-1" || subhead == "1")){
		
		totFieldName = "input_TOTAL EARNING"+"_"+"1"+"_"+"13";
		totl_inputfiield =  document.getElementsByName(totFieldName);
		if(totl_inputfiield != null)
			$(totl_inputfiield).val(totl_unit_val!=0?totl_unit_val:"");
	}
	
	
	if((subhead == "-1" || subhead == "2")){
		totFieldName = "input_TOTAL EARNING"+"_"+"2"+"_"+"13";
		totl_inputfiield =  document.getElementsByName(totFieldName);
		if(totl_inputfiield != null)
			$(totl_inputfiield).val(totl_amount_val!=0?totl_amount_val:"");	
	}
	 
}

function getMonth(val){
	
	let month = "";
	switch (val){
	
		case 1:
			month = "04";
			break;
		case 2:
			month = "05";
			break;
		case 3:
			month = "06";
			break;
		case 4:
			month = "07";
			break;
		case 5:
			month = "08";
			break;
		case 6:
			month = "09";
			break;
		case 7:
			month = "10";
			break;
		case 8:
			month = "11";
			break;
		case 9:
			month = "12";
			break;
		case 10:
			month = "01";
			break;
		case 11:
			month = "02";
			break;
		case 12:
			month = "03";
			break;
		case 13:
			month = "total";
			break;
		default:
			month = "";				
	}
	return month;
}


function getIndexFromMonth(val){
	
	let index = "";
	switch (val){
	
		case "04":
			index = "1";
			break;
		case "05":
			index = "2";
			break;
		case "06":
			index = "3";
			break;
		case "07":
			index = "4";
			break;
		case "08":
			index = "5";
			break;
		case "09":
			index = "6";
			break;
		case "10":
			index = "7";
			break;
		case "11":
			index = "8";
			break;
		case "12":
			index = "9";
			break;
		case "01":
			index = "10";
			break;
		case "02":
			index = "11";
			break;
		case "03":
			index = "12";
			break;
		default:
			index = "";				
	}
	return index;
}

function clearClicked(){
	location.reload();
}

function recalculate(el){
	
	const rowKey = el.dataset.row;
	const colKey = el.dataset.col;

	calculateRowTotal(rowKey);
	calculateColumnTotal(colKey);
	calculateGrandTotals();
}

function parseVal(v) {
    return v === "" || isNaN(v) ? 0 : parseFloat(v);
}

function calculateRowTotal(rowKey) {

	let sum = 0;
    document.querySelectorAll('input[data-row='+rowKey+']')
        .forEach(inp =>{ 	
		
				if (inp.dataset.col !== "TOT") {
	            	sum += parseVal(inp.value);
	        	}
		});
			
    const totalCell = document.querySelector("input[data-row="+rowKey+"][data-col='TOT']" );
    if (totalCell) {
        totalCell.value = sum.toFixed(2);
    }
}

function calculateColumnTotal(colKey) {
	
	let unitSum = 0;
	let amtSum  = 0;

	document.querySelectorAll("input[data-col='"+colKey+"']")
	        .forEach(inp => {
				
				
	            if (inp.disabled || inp.readOnly) return;

	            const val = parseVal(inp.value);

	            if (inp.dataset.row.endsWith("_UNIT")) {
	                unitSum += val;
	            } 
	            else if (inp.dataset.row.endsWith("_AMT")) {
	                amtSum += val;
	            }
	        });

	    /* TEARN_UNIT column total */
	    const unitTotal = document.querySelector( "input[data-row='TEARN_UNIT'][data-col='"+colKey+"']");
	    if (unitTotal) {			
	        unitTotal.value = unitSum !=0.0?unitSum.toFixed(2):"";
	    }

	    /* TEARN_AMT column total */
	    const amtTotal = document.querySelector("input[data-row='TEARN_AMT'][data-col='"+colKey+"']" );
	    if (amtTotal) {
	        amtTotal.value = amtSum !=0.0?amtSum.toFixed(2):"";
	    }
}

function calculateGrandTotals() {
    /* Recalculate TOT column for TOTAL row */
    calculateRowTotal("TEARN_UNIT");
    calculateRowTotal("TEARN_AMT");
}


function resetAllFormAndData(){
	
	const topform = document.getElementById("top-form");
	topform.reset();
	
	const bottomform = document.getElementById("bottom-form");
	bottomform.reset();
	
	formdata = {};
	
	/* formtotal = {}; */
}


function resetBottomForm(){
	
	formdata = {};
	const bottomform = document.getElementById("bottom-form");
	bottomform.reset();
}
