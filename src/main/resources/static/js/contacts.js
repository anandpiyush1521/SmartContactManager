console.log("contacts.js")

const viewContactModal = document.getElementById('view_contact_modal');

// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal(){
    contactModal.show();
}

function closeContactModal(){
    contactModal.hide();
}

async function loadContactData(id){

    //function call to load data
    console.log(id);

    try {
        const data = await( 
            await fetch(`http://localhost:8080/api/contacts/${id}`)
        ).json();
        console.log(data);

        document.querySelector("#contact_name").innerHTML=data.name;
        document.querySelector("#contact_email").innerHTML=data.email;

        openContactModal();

    } catch (error) {
        console.log("Error: ",error);
    }


    // fetch(`http://localhost:8080/api/contacts/${id}`)
    // .then(async(response) => {
    //     const data = await response.json();
    //     console.log(data);
    //     return data;
    // }).catch((error) => {
    //     console.log(error);
    // })
}