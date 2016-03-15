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
            <div class="debateCreationHeader">
                Create a debate..
            </div>
        </div>
    </div>
    <div class="row debateCreationBody">
        <div class="col-md-7 options">
            <div class="innerHeader">
                General Setup
            </div>
            <div class="modal-body">
                <div class="well well-sm">Tell us something about what you want to discuss. :)</div>
                <form id="createForm" action="." method="post">
                    <div class="form-group">

                        <input type="hidden" name="action" value="debate">
                        <input type="hidden" name="command" value="create">

                        <label for="createTopic">Topic</label>                        
                        <input class="form-control" id="createTopic" type="text" name="topic" placeholder="e.g. Iphone or Andoid?" autocomplete="off" />

                        <label for="createTopic">Description</label>    
                        <input class="form-control" id="createDescription" type="text" name="description" placeholder="e.g. What do you prefer? An Apple iPhone or an Android-Phone?" autocomplete="off" />

                        <label for="createTopic">
                            Hastags <i data-toggle="tooltip" data-placement="right" title="With this tags other users are able to get your debate." class="glyphicon glyphicon-info-sign"></i>
                        </label></br>
                        <input id="createTags" data-role="tagsinput" class="form-control" type="text" name="tags" placeholder="Tags" autocomplete="off" /></br>

                        <label for="createTopic">
                            Closing-Date <i data-toggle="tooltip" data-placement="right" title="Date, when the debate will be closed!" class="glyphicon glyphicon-info-sign"></i>
                        </label>    
                        <input class="form-control datepicker" data-date-format="dd.mm.yyyy" data-provide="datepicker" id="createClosingDate" type="date" name="closingDate" placeholder="e.g. 01.01.2017" readonly="true"/>
                        <div class="modal-footer">
                            <input class="btn btn-default" id="createDebateBtn" value="Create">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4 col-md-offset-1 options">
            <div class="innerHeader">
                Administration
            </div>
            <div class="modal-body">
                some checkboxes here..
            </div>
        </div>

    </div>
</div>


