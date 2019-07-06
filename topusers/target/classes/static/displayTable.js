window.addEventListener('load',function(){
	console.log('All assets are loaded');
	//displayData();
}); 


function generateTableHead(table, data) {
	  let thead = table.createTHead();
	  let row = thead.insertRow();
	  for (let key of data) {
	    let th = document.createElement("th");
	    let text = document.createTextNode(key);
	    th.appendChild(text);
	    row.appendChild(th);
	  }
	}



	function removeElement() {
		var Table = document.getElementById("table");
	  Table.innerHTML = "";
	}
	
	function generateTable(table, data) {
		  for (let element of data) {
		    let row = table.insertRow();
		    for (key in element) {
		      let cell = row.insertCell();
		      let text = document.createTextNode(element[key]);
		      cell.appendChild(text);
		    }
		  }
		}
		function show(data){
		removeElement();
		let table = document.querySelector("table");
		let datas = ['User','Number Of Messages','Frequent Message'];
		generateTableHead(table, datas);
		generateTable(table, data);
		}