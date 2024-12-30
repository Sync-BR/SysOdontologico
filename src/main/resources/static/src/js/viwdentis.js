const modal = document.getElementById("dentistModal");
const btns = document.querySelectorAll(".btn-show-details");
const span = document.getElementsByClassName("close")[0];
btns.forEach(function(btn) {
    btn.addEventListener("click", function() {
        document.getElementById("modalId").textContent = btn.getAttribute("data-id");
        document.getElementById("modalName").textContent = btn.getAttribute("data-name");
        document.getElementById("modalCpf").textContent = btn.getAttribute("data-cpf");
        document.getElementById("modalEmail").textContent = btn.getAttribute("data-email");
        document.getElementById("modalPhone").textContent = btn.getAttribute("data-phone");
        document.getElementById("modalDateOfBirth").textContent = btn.getAttribute("data-dateOfBirth");
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