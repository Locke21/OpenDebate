<%-- 
    Document   : NewDebate
    Created on : Mar 7, 2016, 3:27:38 PM
    Author     : D062572
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container">
    <div class="row">
        <div class="col-lg-8">
            <h2>Create debate</h2>
            <form action="." method="post">
                <div class="form-group">

                    <input type="hidden" name="action" value="debate">
                    <input type="hidden" name="command" value="create">
                    <input class="form-control" type="text" name="topic" placeholder="Topic">
                    <input class="form-control" type="text" name="description" placeholder="Description">
                    <input class="form-control" type="text" name="tags" placeholder="Tags">
                    <input class="form-control" type="date" name="closingDate" placeholder="1.1.2017">
                    <input class="btn btn-default"type="submit" value="Create">
                </div>
            </form>
        </div>
    </div>
</div>