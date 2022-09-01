function setLanguageEN() {

    $.ajax({
        type: 'GET',
        url: 'AjaxLanguageSetServlet?',
        data: {language: "EN"},
        success: function (resultText) {
            $('#result').html(resultText);
        },
        error: function (jqXHR, exception) {
            console.log('Error occured!!');
        }
    });
}