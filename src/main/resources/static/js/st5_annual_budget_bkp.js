/**
 * 
 */

let formdata ={};
/* let formtotal ={}; */
const allheads =["PASSENGER","OTHER COACHING","ORIGINATING GOODS","OTHER GOODS","SUNDRY","PASSENGER Units"];
let isBottomFormdisabled = true;

document.addEventListener('DOMContentLoaded', function () {
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
});


$(function(){              // shorthand for $(document).ready(...)
  
	$('#top-fieldset').on('change input', 'input, select', function() {
		
		/*console.log('Value changed in:');*/

		if(!isBottomFormdisabled){
			
			isBottomFormdisabled = !isBottomFormdisabled;
			resetBottomForm();
			$("#formFields").prop("disabled",true);	
		}
			
	    
	});
	
});



function checkValue(ele){
	
	/*resetBottomForm();*/
	let year = ele.value;	
	if(year !=null && year.length == 4){
		
		let nextyear = +year.slice(-2) + 1;
		console.log("nextyear = " +nextyear);
		$("#txt-financial-year-2").val(nextyear);		
		getMaxBudgetNumber();
	}
	else{
		
		$("#formFields").prop('disabled', true);	
		$("#txt-financial-year-1").val("");
		$("#txt-financial-year-2").val("");
		alert("Enter valid year.");
	}	
	
}

function budgetNoChanged(ele){
	
	/*resetBottomForm();*/
	let budgetno = ele.value;
	if(budgetno == '1' || budgetno == '-1'){
		$("#for-month").val("04");
		$("#for-month").prop('disabled', true);
	}
	else{
		
		getForMonthForBudgetNo(budgetno);
		/*$("#for-month").prop('disabled', false);*/
	}	
	
}

function monthChanged(ele){
	
	/*resetBottomForm();*/
	resetAllReadonlyTextfields();

	let monthval = $("#for-month").val();
	if(monthval === "-1" || monthval === "04" || monthval === "05")
		return;
	
	makeTextReadonlyForMonth(monthval);
}

function resetAllReadonlyTextfields(){

	/*console.log("resetAllReadonlyTextfields called");*/
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

function makeTextReadonlyForMonth(formonth){
	
	/*console.log("makeTextReadonlyForMonth called 1");*/

	
	if(formonth ==null || formonth.length == 0)
		return;
	/*console.log("makeTextReadonlyForMonth called 2");*/

	let indexForMonth =  +getIndexFromMonth(formonth);
	let month_index_to_disable = indexForMonth - 2;
	if(month_index_to_disable < 1)
		return;
	
	let tempfield_name = null;
	let inputfiield = null;
	allheads.forEach((head, index) => {
	
		for(let i=1;i<=month_index_to_disable;i++){
			
			if(head !== "PASSENGER Units"){
				
				tempfield_name = "input_" + head + "_" + "1" + "_" + i;
				inputfiield = document.getElementsByName(tempfield_name);
				if (inputfiield != null){
					
					$(inputfiield).prop('readonly', true);
					
				}
					

				tempfield_name = "input_" + head + "_" + "2" + "_" + i;
				inputfiield = document.getElementsByName(tempfield_name);
				if (inputfiield != null){
					
					$(inputfiield).prop('readonly', true);
				}					
					

			}else{
				
				tempfield_name = "input_" + head + "_" + i;
				inputfiield = document.getElementsByName(tempfield_name);
				if (inputfiield != null)
					$(inputfiield).prop('readonly', true);
			}
			
		}
		
	});
	
}


function getForMonthForBudgetNo(budgetno){

	let fy = $("#txt-financial-year-1").val() + "-" + $("#txt-financial-year-2").val();
	if (fy.length == 7 && budgetno != null && budgetno.length >0) {
		
		var jsondata = {
			financialyear: fy,
			budgetnumber: budgetno		
		};

		$.ajax({
			url: "/annual_budget/getForMonthForBudgetnumber",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(jsondata),
			success: function(response) {

				if (response.status === "success") {
					
					if(response.formonth === "-1"){
						$("#for-month").prop('disabled', false);	
						$("#for-month").val("-1");
					}					
					else{
						
						$("#for-month").val(response.formonth);
						$("#for-month").prop('disabled', true);
					}
						
					resetAllReadonlyTextfields();
					makeTextReadonlyForMonth(response.formonth);
					
				} else {

					$("#for-month").prop('disabled', true);
					console.log("Some error occured.");
				}
			},
			error: function() {
				$("#for-month").prop('disabled', true);
				alert("Something went wrong!");
			}
		});

		
	}else{
		console.log("Invalid data.");
	}
}

function getMaxBudgetNumber(){
	
	let fy = $("#txt-financial-year-1").val() + "-" + $("#txt-financial-year-2").val();
	if (fy.length == 7) {

		var jsondata = {
			financialyear: fy
		};

		$.ajax({
			url: "/annual_budget/getMaxBudgetnumber",
			type: "POST",
			contentType: "application/json",
			data: JSON.stringify(jsondata),
			success: function(response) {

				if (response.status === "success") {

					$("#budget-no").prop("disabled", false);
					$("#division").prop("disabled", false);					
					resetBudgetnoList(response.maxbudgetno);					
					
				} else {

					console.log("Some error occured.");
				}
			},
			error: function() {
				alert("Something went wrong!");
			}
		});

	} else {

		console.log("Invalid financial Year")
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
	}else if(budgetno == "-1"){
		alert("Select budget number.")
	}else if(formonth == "-1"){
		alert("Select for month.")
	}else if(division == "-1"){
		alert("Select division.")
	}else{
				
		/*let fy = fy1+"-"+fy2;
		
		 var jsondata = {
			financialyear: fy,
			budgetno: budgetno,
			formonth:formonth,
			division:division,
   		};
		
		 console.log("jsondata = "+JSON.stringify(jsondata));*/
		let formData = $("#top-form").serialize();
		$.ajax({
           url: "/annual_budget/getbudgetdata",
           type: "POST",
           data: formData,
           success: function (response) {
			
           	console.log("response ="+response.status);
               if (response.status === "success") {
               		
				isBottomFormdisabled = false;
           		$("#formFields").prop("disabled",false);
				

           		/* console.log("data from DB ="+JSON.stringify(response.data)); */
            	   $.each(response.data, function(index, annualbudget) {
            		   
            		   let head = annualbudget.head; 
            		   let subhead = (annualbudget.subhead === "Units"?"1":"2");
            		   let monthindex = getIndexFromMonth(annualbudget.month);
            		   let field_name = "";
            		   let tol_field_name = "";
            		   if(head === "PASSENGER Units"){
            			   field_name = "input_"+head+"_"+monthindex;
            			   tol_field_name = "input_"+head+"_"+"13";
            		   }            			   
            		   else{
            			   
            			   field_name = "input_"+head+"_"+subhead+"_"+monthindex;
            			   tol_field_name = "input_"+head+"_"+subhead+"_"+"13";
            		   }
            			                   		   
            		   
            		   if (!formdata[field_name]) {
        				   formdata[field_name] = {};   // create new key
               			}
        			   
        			   formdata[field_name]["recordid"] = annualbudget.recordid;
        			   formdata[field_name]["orgval"] = annualbudget.value;
        			   formdata[field_name]["isedited"] = 0;                			   
        			   formdata[field_name]["newval"] = annualbudget.value;
        			   
        			   let inputfiield =  document.getElementsByName(field_name);
        			   $(inputfiield).val(annualbudget.value >0?annualbudget.value:"");  
            		  
       			}); 
            	 
            	   updateTotalValues();
            	 /* console.log("my constructed data = "+JSON.stringify(formdata)); */
            	   
               } else {
               	
					isBottomFormdisabled = true;
					$("#formFields").prop("disabled",true);
                   alert("Some error occured.");
               }
           },
           error: function () {
				isBottomFormdisabled = true;
				$("#formFields").prop("disabled",true);
               alert("Something went wrong!");
           }
       }); 
		
	}	
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
				
		/* let tabledata = getJsonFormData(); */
		if (Object.keys(formdata).length === 0) {
		    
			alert("Enter month wise data.");
			
		} else {
			
		    
			let fy = fy1+"-"+fy2;
			
			 let jsondata = {
				financialyear: fy,
				budgetno: budgetno,
				formonth:formonth,
				division:division,
				data : formdata
	    	};
			
			console.log("jsondata = "+JSON.stringify(jsondata));			
			$.ajax({
	            url: "/annual_budget/savebudgetdata",
	            type: "POST",
	            contentType: "application/json",
	            data: JSON.stringify(jsondata),
	            success: function (response) {
	            	console.log("response ="+response);
	                if (response.status === "success") {
	                	
						$("#status_span").text("Data saved successfully!");	                	
	                	$("#status_span").removeClass('error').addClass('success');
	                	
	                } else {
	                	
	                	$("#status_span").text("Some error occured.");
	                	$("#status_span").removeClass('success').addClass('error');
	                }
	                
	                resetAllFormAndData();
	            },
	            error: function () {
	                alert("Something went wrong!");
	                resetAllFormAndData();
	            }
	        }); 
	        
	        
		}		
		
	}	
	
}


function captureFormValue(ele){
	
	if ($(ele).is('[readonly]')) {
		console.log("readonly")
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
	
	
	/* console.log("edited data = " +JSON.stringify(formdata)); */	
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
		
		/* console.log("totFieldName = "+totFieldName +"value = "+totlval); */
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
					
			/* console.log("totFieldName = "+totFieldName +"value = "+totlval); */
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
		/* console.log("Vertical toal unit field name ="+totFieldName + "& value = "+totl_unit_val); */
		totl_inputfiield =  document.getElementsByName(totFieldName);
		if(totl_inputfiield != null)
			$(totl_inputfiield).val(totl_unit_val!=0?totl_unit_val:"");
		
		totFieldName = "input_TOTAL EARNING"+"_"+"2"+"_"+i;
		/* console.log("Vertical toal unit amount name ="+totFieldName + "& value = "+totl_amount_val); */
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
	resetAllFormAndData();
	$("#formFields").prop("disabled",true);	
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
