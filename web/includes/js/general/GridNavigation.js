/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Pager(tableName, itemsPerPage) {
   
    this.tableName = tableName;
    this.itemsPerPage = itemsPerPage;
    this.currentPage = 1;
    this.pages = 0;
    this.inited = false;

    this.showRecords = function(from, to) {
        var rows = document.getElementById(tableName).rows;
        // i starts from 1 to skip table header row
        for (var i = 1; i < rows.length; i++) {
            if (i < from || i > to)
                rows[i].style.display = 'none';
            else
                rows[i].style.display = '';
        }
    }

    this.showPage = function(pageNumber) {
        if (! this.inited) {
           // alert("not inited");
            return;
        }

        var oldPageAnchor = document.getElementById('pg'+this.currentPage);
        oldPageAnchor.className = 'pg-normal';

        this.currentPage = pageNumber;
        var newPageAnchor = document.getElementById('pg'+this.currentPage);
        newPageAnchor.className = 'pg-selected';

        var from = (pageNumber - 1) * itemsPerPage + 1;
        var to = from + itemsPerPage - 1;
        this.showRecords(from, to);
    }

    this.prev = function() {
        if (this.currentPage > 1)
            this.showPage(this.currentPage - 1);
    }

    this.next = function() {
        if (this.currentPage < this.pages) {
            this.showPage(this.currentPage + 1);
        }
    }

    this.init = function() {
        var rows = document.getElementById(tableName).rows;
        var records = (rows.length -1);
        this.pages = Math.ceil(records / itemsPerPage);
        this.inited = true;
    }

    this.showPageNav = function(pagerName, positionId) {
        if (! this.inited) {
            //alert("not inited");
            return;
        }
        //alert(positionId)
        //alert(pagerName)
        var element = document.getElementById(positionId);
        var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal" "> <font align="bottom" class="jumpbar"> Page: <i class="fa fa-chevron-circle-left"></i> </font></span> ';
        for (var page = 1; page <= this.pages; page++) 
            pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');" "><font color="black" face="verdana">' + page + '</font></span> ';
        pagerHtml += '<span onclick="'+pagerName+'.next();" class="pg-normal"><font align="bottom" class="jumpbar"><i class="fa fa-chevron-circle-right"></i></font></span>';            
       
        // pagerHtml='<span style="margin-right:40vw;>'+pagerHtml+'</span>';
        element.innerHTML =pagerHtml ;
    }
}


// DBGrid.js file start



function doNavigate(pstrWhere, pintTot)
{
  var strTmp;
  var intPg;

  strTmp = document.frmDBGrid.txtCurr.value;
  intPg = parseInt(strTmp);
  if (isNaN(intPg)) intPg = 1;
  if ((pstrWhere == 'F' || pstrWhere == 'P') && intPg == 1)
  {
    alert("You are already viewing first page!");
    return;
  }
  else if ((pstrWhere == 'N' || pstrWhere == 'L') && intPg == pintTot)
  {
    alert("You are already viewing last page!");
    return;
  }
  if (pstrWhere == 'F')
    intPg = 1;
  else if (pstrWhere == 'P')
    intPg = intPg - 1;
  else if (pstrWhere == 'N')
    intPg = intPg +1;
  else if (pstrWhere == 'L')
    intPg = pintTot;
  if (intPg < 1) intPg = 1;
  if (intPg > pintTot) intPg = pintTot;
  document.frmDBGrid.txtCurr.value = intPg;
  document.frmDBGrid.submit();
}
function doSort(pstrFld, pstrOrd)
{
  document.frmDBGrid.txtSortCol.value = pstrFld;
  document.frmDBGrid.txtSortAsc.value = pstrOrd;
  document.frmDBGrid.submit();
}

function goToPage(element) {
    document.frmDBGrid.txtCurr.value = element.options[element.selectedIndex].value;
    document.frmDBGrid.submit();
}
function goToMyPage(element) {
    if (element == null || element.value == null
    		|| element.value == ''){
    	return;
    }
	document.frmDBGrid.txtCurr.value = element.value;
	document.frmDBGrid.submit();
}

//responsive_tables.js start


$(document).ready(function() {
  var switched = false;
  var updateTables = function() {
    if (($(window).width() < 1025) && !switched ){
    
     switched = true;
      $("table.responsive").each(function(i, element) {
        splitTable($(element));
      });
      return true;
    }
    else if (switched && ($(window).width() > 1025)) {
      switched = false;
      $("table.responsive").each(function(i, element) {
        unsplitTable($(element));
      });
    }
  };

  $(window).load(updateTables);
  $(window).on("redraw",function(){switched=false;updateTables();}); // An event to listen for
  $(window).on("ready", updateTables);


	function splitTable(original)
	{
		original.wrap("<div class='table-wrapper' />");

		var copy = original.clone();
		copy.find("td:not(:first-child), th:not(:first-child)").css("display", "none");
		copy.removeClass("responsive");

		original.closest(".table-wrapper").append(copy);
		copy.wrap("<div class='pinned' />");
		original.wrap("<div class='scrollable' />");

    setCellHeights(original, copy);
	}

	function unsplitTable(original) {
    original.closest(".table-wrapper").find(".pinned").remove();
    original.unwrap();
    original.unwrap();
	}

  function setCellHeights(original, copy) {
    var tr = original.find('tr'),
        tr_copy = copy.find('tr'),
        heights = [];

    tr.each(function (index) {
      var self = $(this),
          tx = self.find('th, td');

      tx.each(function () {
        var height = $(this).outerHeight(true);
        heights[index] = heights[index] || 0;
        if (height > heights[index]) heights[index] = height;
      });

    });

    tr_copy.each(function (index) {
      $(this).height(heights[index]);
    });
  }

});
