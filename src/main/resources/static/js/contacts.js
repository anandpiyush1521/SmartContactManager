console.log("contacts.js");

const baseURL = "http://localhost:8080";

const viewContactModal = document.getElementById("view_contact_modal");
const imageModal = document.getElementById("image_modal");

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => console.log("modal is hidden"),
  onShow: () => console.log("modal is shown"),
  onToggle: () => console.log("modal has been toggled"),
};

// instance options object
const instanceOptions = { id: "view_contact_modal", override: true };

const contactModal = new Modal(viewContactModal, options, instanceOptions);

const imageModalOptions = {
  placement: "center",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => console.log("image modal is hidden"),
  onShow: () => console.log("image modal is shown"),
  onToggle: () => console.log("image modal has been toggled"),
};

const imageModalInstance = new Modal(imageModal, imageModalOptions);

function openContactModal() {
  contactModal.show();
}

function closeContactModal() {
  contactModal.hide();
}

function openImageModal() {
  imageModalInstance.show();
}

function closeImageModal() {
  imageModalInstance.hide();
}

async function loadContactData(id) {
  console.log(id);
  try {
    const response = await fetch(`${baseURL}/api/contacts/${id}`);
    const data = await response.json();
    console.log(data);

    document.querySelector("#contact_name").innerHTML = data.name;
    document.querySelector("#contact_email").innerHTML = data.email;
    document.querySelector("#contact_phone").innerHTML = data.phoneNumber;
    document.querySelector("#contact_address").innerHTML = data.address;
    document.querySelector("#contact_description").innerHTML = data.description;
    document.querySelector("#contact_picture").src = data.picture;

    // Update the large image modal with the contact picture
    document.querySelector("#large_contact_picture").src = data.picture;

    // Display website link
    const websiteElement = document.querySelector("#contact_website");
    if (data.websiteLink) {
      websiteElement.innerHTML = `<a href="${data.websiteLink}" target="_blank">Website</a>`;
    } else {
      websiteElement.innerHTML = "";
    }

    // Display LinkedIn link
    const linkedinElement = document.querySelector("#contact_linkedin");
    if (data.linkedInLink) {
      linkedinElement.innerHTML = `<a href="${data.linkedInLink}" target="_blank">LinkedIn</a>`;
    } else {
      linkedinElement.innerHTML = "";
    }

    // Check if the contact is a favorite
    if (data.favorite) {
      document.querySelector("#favorite_star").classList.remove("hidden");
      document
        .querySelector("#contact_picture")
        .classList.add("border-4", "border-green-500");
    } else {
      document.querySelector("#favorite_star").classList.add("hidden");
      document
        .querySelector("#contact_picture")
        .classList.remove("border-4", "border-green-500");
    }

    openContactModal();
  } catch (error) {
    console.log("Error: ", error);
  }
}

// Delete contact
async function deleteContact(id) {
  Swal.fire({
    title: "Do you want to delete the contact?",
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Delete",
  }).then((result) => {
    if (result.isConfirmed) {
      const url = `${baseURL}/user/contacts/delete/${id}`;
      window.location.replace(url);
    }
  });
}
