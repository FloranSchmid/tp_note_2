<%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <html>

    <head>
        <title>Movies</title>
        <meta charset="UTF-8" />
    </head>

    <body>
        <fieldset>
            <legend>Liste des films</legend>
            <table style="border: 1px solid;">
                <tr>
                    <th>Titre</th>
                    <th>Annee</th>
                    <th>Acteurs</th>
                    <th>Affiche</th>
                    <th>Baisser</th>
                    <th>Note</th>
                    <th>Augmenter</th>

                </tr>
                <c:forEach items="${requestScope.MOVIES_LIST}" var="movie">
                    <tr>
                        <td>${movie.title}</td>
                        <td>${movie.year}</td>
                        <td>${movie.actors}</td>
                        <td><img style="height: 300px; width: 200px;" src="${movie.imgPoster}" /></td>
                        <td>${movie.decreaseURL}</td>
                        <td>${movie.note}</td>
                        <td>${movie.increaseURL}</td>
                    </tr>
                </c:forEach>
            </table>

        </fieldset>
    </body>

    </html>