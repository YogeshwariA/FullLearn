<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <script type="text/javascript" src="js/dashboard.js"></script>
      <title>Full Learn</title>
      <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
         rel="stylesheet">
      <link rel="stylesheet" href="css/materialize.min.css"
         media="screen,projection" />
      <!-- Bootstrap Styles-->
      <link href="css/bootstrap.css" rel="stylesheet" />
      <!-- FontAwesome Styles-->
      <link href="css/font-awesome.css" rel="stylesheet" />
      <!-- Custom Styles-->
      <link href="css/custom-styles.css" rel="stylesheet" />
      <!-- Google Fonts-->
      <link href='http://fonts.googleapis.com/css?family=Open+Sans'
         rel='stylesheet' type='text/css' />
   </head>
   <body>
      <jsp:useBean id="user" scope="session" class="com.fulllearn.model.User"></jsp:useBean>
      <div id="wrapper">
         <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
               <a class="navbar-brand waves-effect waves-dark"
                  class="large material-icons" href="images/fav-icon.png"> <strong>FullLearn</strong></a>
            </div>
            <ul class="nav navbar-top-links navbar-right">
               <li><a class="dropdown-button waves-effect waves-dark"
                  href="#!" data-activates="dropdown1"> <img class="img"
                  id="profilePic" src="${user.photoId}"></img> <b>${user.firstName}</b>
                  <i class="material-icons right">arrow_drop_down</i></a>
               </li>
            </ul>
         </nav>
         <!-- Dropdown Structure -->
         <ul id="dropdown1" class="dropdown-content">
            <li><a href="#"><i class="fa fa-user fa-fw"></i> My Profile</a>
            </li>
            <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
            <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
         </ul>
         <!--/. NAV TOP  -->
         <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
               <ul class="nav" id="main-menu">
                  <li><a id="dashboard" href="javascript:getUserDetails()"
                     class="active-menu waves-effect waves-dark"><i
                     class="fa fa-dashboard"></i>Dashboard</a></li>
                  <!-- <li><a class="waves-effect waves-dark">
                     <i class="fa fa-desktop"></i> Feeds</a>
                     </li>
                     <li><a class="waves-effect waves-dark">
                     <i class="fa fa-bar-chart-o"></i> Peers</a>
                     </li> -->
               </ul>
            </div>
         </nav>
         <!-- /. NAV SIDE  -->
         <div id="page-wrapper">
            <div class="header">
               <!-- <h1 class="page-header">Dashboard</h1>
                  <ol class="breadcrumb">
                  	<li><a href="#">Home</a></li>
                  	<li><a href="#">Dashboard</a></li>
                  	<li class="active">Data</li>
                  </ol>
                  -->
            </div>
            <div id="page-inner">
               <div class="dashboard-cards">
                  <div class="row">
                     <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="card horizontal cardIcon waves-effect waves-dark">
                           <div class="card-image red"></div>
                           <div class="card-stacked red">
                              <div class="card-content">
                                 <h3>0</h3>
                              </div>
                              <div class="card-action">
                                 <strong>00:00</strong>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="card horizontal cardIcon waves-effect waves-dark">
                           <div class="card-image orange"></div>
                           <div class="card-stacked orange">
                              <div class="card-content">
                                 <h3>0</h3>
                              </div>
                              <div class="card-action">
                                 <strong>00:00</strong>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="card horizontal cardIcon waves-effect waves-dark">
                           <div class="card-image blue"></div>
                           <div class="card-stacked blue">
                              <div class="card-content">
                                 <h3>0</h3>
                              </div>
                              <div class="card-action">
                                 <strong>00:00</strong>
                              </div>
                           </div>
                        </div>
                     </div>
                     <div class="col-xs-12 col-sm-6 col-md-3">
                        <div class="card horizontal cardIcon waves-effect waves-dark">
                           <div class="card-image green"></div>
                           <div class="card-stacked green">
                              <div class="card-content">
                                 <h3>0</h3>
                              </div>
                              <div class="card-action">
                                 <strong>00:00</strong>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </body>
</html>