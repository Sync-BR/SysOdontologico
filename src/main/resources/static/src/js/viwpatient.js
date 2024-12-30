const searchInput = document.getElementById("searchInput");
const tableBody = document.getElementById("tableBody");
searchInput.addEventListener("input", function() {
    const filter = searchInput.value.toLowerCase();
    const rows = tableBody.getElementsByTagName("tr");

    for (let i = 0; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName("td");
        let match = false;
        for (let j = 0; j < cells.length - 1; j++) {
            if (cells[j].textContent.toLowerCase().includes(filter)) {
                match = true;
                break;
            }
        }
        rows[i].style.display = match ? "" : "none";
    }
});

var modal = document.getElementById("patientModal");
var btns = document.querySelectorAll(".btn-show-details");
var span = document.getElementsByClassName("close")[0];
btns.forEach(function(btn) {
    btn.addEventListener("click", function() {
        document.getElementById("modalId").textContent = btn.getAttribute("data-id");
        document.getElementById("modalName").textContent = btn.getAttribute("data-name");
        document.getElementById("modalCpf").textContent = btn.getAttribute("data-cpf");
        document.getElementById("modalGender").textContent = btn.getAttribute("data-gender");
        document.getElementById("modalDateOfBirth").textContent = btn.getAttribute("data-dateOfBirth");
        document.getElementById("modalTelephone").textContent = btn.getAttribute("data-telephone");
        document.getElementById("modalEmail").textContent = btn.getAttribute("data-email");
        document.getElementById("modalAddress").textContent = btn.getAttribute("data-address");
        modal.style.display = "block";
    });
});

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}