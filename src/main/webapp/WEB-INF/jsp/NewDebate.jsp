<%-- 
    Document   : NewDebate
    Created on : Mar 7, 2016, 3:27:38 PM
    Author     : D062572
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script type="text/javascript" src="/OpenDebate/js/createDebate.js"></script>
<!DOCTYPE html>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h3 class="debateCreationHeader">
                Create a debate..
            </h3>
        </div>
    </div>
    <div class="row debateCreationBody">
        <div class="col-md-8 options col-md-offset-2">
            <h4 class="innerHeader">
                General Information
            </h4>
            <div class="modal-body">
                <form id="createForm" action="." method="post">
                    <div class="form-group">

                        <input type="hidden" name="action" value="debate">
                        <input type="hidden" name="command" value="create">

                        <label for="createTopic">Topic</label>                        
                        <input class="form-control" id="createTopic" type="text" name="topic" placeholder="e.g. Iphone or Andoid?" autocomplete="off" />

                        <label for="createTopic">Description</label>    
                        <input class="form-control" id="createDescription" type="text" name="description" placeholder="e.g. What do you prefer? An Apple iPhone or an Android-Phone?" autocomplete="off" />

                        <label for="createTopic">
                            Tags <i data-toggle="tooltip" data-placement="right" title="Tags let users find your debate easily..." class="glyphicon glyphicon-info-sign"></i>
                        </label></br>
                        <input id="createTags" data-role="tagsinput" class="form-control" type="text" name="tags" placeholder="Tag, Tag, Tag, ..." autocomplete="off" /></br>

                        <label for="createTopic">
                            Closing-Date <i data-toggle="tooltip" data-placement="right" title="Pick a date when the debate should be closed..." class="glyphicon glyphicon-info-sign"></i>
                        </label>    
                        <input class="form-control datepicker" data-date-format="dd.mm.yyyy" data-provide="datepicker" id="createClosingDate" type="text" name="closingDate" placeholder="e.g. 01.01.2017" readonly="true"/>
                        <div class="modal-footer">
                            <input class="btn btn-default" id="createDebateBtn" value="Create">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!--<div class="col-md-4 col-md-offset-1 options">
            <div class="innerHeader">
                Administration
            </div>
            <div class="modal-body">
                some checkboxes here..
            </div>
        </div>-->

    </div>
</div>


