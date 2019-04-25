<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Header -->
<header class="w3-container" style="padding-top: 22px">
	<h5>
		<b><i class="fa fa-dashboard"></i>Danh sách truyện</b>
	</h5>
</header>

<div class="w3-row-padding w3-margin-bottom">
	<section>

		<div class="container">
			<div style="text-align: center">
				<h2 style="color: blue;">Product Management</h2>
				<p>-----------------</p>
			</div>
			<div class="row">
				<div class="column">
					<img src="webapp/images/download.jpg" alt="Snow">
					<button class="btn">Edit</button>
					<div class="row">
						<label for="id">ID</label> <input type="text" id="id" name="id"
							placeholder="id..."> <label for="idCategory">ID
							Category</label> <input type="text" id="idcategory" name="idcategory"
							placeholder="idcategory..."> <label for="idAuthor">ID
							Author</label> <input type="text" id="idAuthor" name="idAuthor"
							placeholder="idAuthor..."> <label for="idPublishCompany">ID
							Publish Company</label> <input type="text" id="idPublishCompany"
							name="idPublishCompany" placeholder="idPublishCompany...">
						<label for="PublishDate">Publishing Date</label> <input
							type="Date" id="PublishDate" name="PublishDate"
							placeholder="PublishDate..">
					</div>
				</div>
				<div class="column">
					<form action="/action_page.php">

						<label for="fname">Product Name</label> <input type="text"
							id="fname" name="name" placeholder="Product name.."> <label
							for="price">Price</label> <input type="text" id="price"
							name="price" placeholder="price.."> <label for="Author">Author</label>
						<input type="text" id="author" name="author"
							placeholder="author.."> <label for="Kind">Kind</label> <select
							id="Kind" name="kind">kind...
							</option>
							<option value="pokemon">special pokemon</option>
							<option value="conan">Conan</option>
						</select> <label for="amount">Amount</label> <input type="text" id="amount"
							name="amount" placeholder="amount.."> <label for="Kind">Kind</label>
						<select id="Kind" name="kind">kind...
							</option>
							<option value="pokemon">special pokemon</option>
							<option value="conan">Conan</option>
						</select> <label for="size">Size</label> <input type="text" id="size"
							name="size" placeholder="size.."> <label for="weight">Weight</label>
						<input type="text" id="weight" name="weight"
							placeholder="weight.."> <label for="bookCover">Book
							Cover</label> <select id="bookCover" name="bookCover">book
							cover...
							</option>
							<option value="soft">soft</option>
							<option value="hard">hard</option>
						</select> <label for="content">Content</label>
						<textarea id="content" name="content" placeholder="content.."
							style="height: 170px"></textarea>
						<input type="submit" value="Create"> <input type="submit"
							value="Cancel" style="background-color: #f3814d;">
					</form>
				</div>
			</div>
		</div>
	</section>
</div>
