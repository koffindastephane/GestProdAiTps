function filterInput(event) {
    var chCode = event.which;
    if (!((chCode >= 65 && chCode <= 90) || (chCode >= 97 && chCode <= 122) || chCode <= 32 || chCode === 39 || chCode === 45)) {
        return false;
    }
}

function filterInputLettresMajEtChiffres(event) {
    var chCode = event.which;
    if (!((chCode >= 48 && chCode <= 57) || (chCode >= 65 && chCode <= 90) || (chCode >= 97 && chCode <= 122) || chCode <= 32 || chCode === 39 || chCode === 45)) {
        return false;
    }
}

function filterInputNombre(event) {
    var chCode = event.which;
    if (!((chCode >= 48 && chCode <= 57) || chCode <= 32)) {
        return false;
    }
}

function filterInputNombreSansEspace(event) {
    var chCode = event.which;
    if (!((chCode >= 48 && chCode <= 57) || chCode < 32)) {
        return false;
    }
}

function filterInputTelephone(event) {
    var chCode = event.which;
    if (!((chCode >= 48 && chCode <= 57) || chCode <= 32 || chCode === 43)) {
        return false;
    }
}

function filterInputDate(event) {
    var chCode = event.which;
    if (!((chCode >= 48 && chCode <= 57) || chCode <= 32 || chCode === 47)) {
        return false;
    }
}

function filterInputDateTime(event) {
    var chCode = event.which;
    if (!((chCode >= 48 && chCode <= 57) || chCode <= 32 || chCode === 47 || chCode === 58 || chCode === 45)) {
        return false;
    }
}

function reset() {
    if (!confirm('Voulez-vous vraiment effacer toute la saisie ?')) {
        return false;
    }
}