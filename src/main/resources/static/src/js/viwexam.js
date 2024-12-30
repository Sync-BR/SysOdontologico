function openModal(imageSrc, description) {
    const modal = document.getElementById("imageModal");
    const modalImage = document.getElementById("modalImage");
    const captionText = document.getElementById("caption");

    modal.style.display = "block";
    modalImage.src = imageSrc;
    captionText.innerHTML = description; // Adiciona descrição se necessário
}

function closeModal() {
    const modal = document.getElementById("imageModal");
    modal.style.display = "none";
}

window.onclick = function(event) {
    const modal = document.getElementById("imageModal");
    if (event.target == modal) {
        modal.style.display = "none";
    }
}