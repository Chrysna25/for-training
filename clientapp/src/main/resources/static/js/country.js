// console.log("Country");

$(document).ready(() => {
  $("#tb-country").DataTable({
    ajax: {
      method: "GET",
      url: "/api/country",
      dataSrc: "",
    },
    columnDefs: [{ className: "text-center", targets: "_all" }],
    columns: [
      { data: "id" },
      { data: "code" },
      { data: "name" },
      { data: "region.name"},
      {
        data: null,
        render: (data) => {
          return /* html */ `
            <div class="d-flex gap-3 justify-content-center align-items-center">
              <button
                type="button"
                class="btn btn-primary btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#detail"
                onclick="findById(${data.id})"
              >
                Detail
              </button>
              <button
                type="button"
                class="btn btn-warning btn-sm"
                data-bs-toggle="modal"
                data-bs-target="#update"
                onclick="beforeUpdate(${data.id})"
              >
                Update
              </button>
              <button
                type="button"
                class="btn btn-danger btn-sm"
                onclick="deleteCountry(${data.id})"
              >
                Delete
              </button>
            </div>
          `;
        },
      },
    ],
  });
});

$("#create-country").click((event) => {
  event.preventDefault();
  const valueName = $("#create-name").val();
  // console.log(valueName);
  if (valueName === "" || valueName === null) {
    Swal.fire({
      position: "center",
      icon: "error",
      title: "Please fill all field!!!",
      showConfirmButton: false,
      timer: 1500,
    });
  } else {
    $.ajax({
      method: "POST",
      url: "/api/country",
      dataType: "JSON",
      contentType: "application/json",
      data: JSON.stringify({
        name: valueName,
      }),
      success: (res) => {
        // console.log(res);
        $("#create").modal("hide");
        $("#tb-country").DataTable().ajax.reload();
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Your Region has been saved",
          showConfirmButton: false,
          timer: 1500,
        });
        $("#create").val("");
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
});

function findById(id) {
  console.log(id)
  $.ajax({
    type: "GET",
    url: `/api/country/${id}`,
    success: function (data) {
      $("#detail-id").val(data.id)
      $("#detail-code").val(data.code)
      $("#detail-name").val(data.name)
      $("#detail-region").val(data.region.name)
    },
  });
}

function beforeUpdate(id) {
  $.ajax({
    type: "GET",
    url: `/api/country/${id}`,
    success: function (data) {
      $("#update-id").val(data.id);
      $("#update-name").val(data.name);
      $("#update-code").val(data.code);
    },
  });
}

function updateCountry() {
  const id = $("#update-id").val();
  const name = $("#update-name").val();
  const code = $("#update-code").val();

  $.ajax({
    type: "PUT",
    url: `/api/country/${id}`,
    data: JSON.stringify({ name: name, code: code }),
    contentType: "application/json",
    success: function (data) {
      Swal.fire({
        position: "center",
        icon: "success",
        title: "Country updated successfully",
        showConfirmButton: false,
        timer: 1500,
      });
      $("#tb-country").DataTable().ajax.reload();
    },
  });
}

function deleteCountry(id) {
  Swal.fire({
    title: "Are you sure?",
    text: "You will not be able to recover this country!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",
  }).then((result) => {
    if (result.isConfirmed) {
      $.ajax({
        type: "DELETE",
        url: `/api/country/${id}`,
        success: function (data) {
          Swal.fire({
            position: "center",
            icon: "success",
            title: "Country deleted successfully",
            showConfirmButton: false,
            timer: 1500,
          });
          $("#tb-country").DataTable().ajax.reload();
        },
      });
    }
  });
}