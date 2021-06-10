let map, infoWindow;

function initMap(nommap) {
	
	  map = new google.maps.Map(document.getElementById("map"), {
    center: { lat: 50.630, lng: 3.057 },
    zoom: 14,
  });
  infoWindow = new google.maps.InfoWindow();
  const locationButton = document.createElement("button");
  locationButton.textContent = "Centrer sur ma position";
  locationButton.classList.add("custom-map-control-button");
  const image = "/images/home.jpg";
  map.controls[google.maps.ControlPosition.TOP_CENTER].push(locationButton);
  locationButton.addEventListener("click", () => {
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        (position) => {
          const pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          marker = new google.maps.Marker({
        	    position: pos ,
        	    map,
        	    title: "locations",
        	    icon: image,
        	  });

          	const infowindow2 = new google.maps.InfoWindow({
                  content: '<h1>Vous êtes Ici !</h1>'
                  
              });
          	 marker.addListener("click", () => {
          		    infowindow2.open(map, marker);
                            console.log(mylist[0]);
          		  });
          infowindow2.open(map);
          map.setCenter(pos);
         
        },
        () => {
          handleLocationError(true, infowindow2, map.getCenter());
        }
      );
    } else {
      // Browser doesn't support Geolocation
      handleLocationError(false, infowindow2, map.getCenter());
    }

   
  });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
  infoWindow.setPosition(pos);
  infoWindow.setContent(
    browserHasGeolocation
      ? "Error: The Geolocation service failed."
      : "Error: Your browser doesn't support geolocation."
  );
  infoWindow.open(map);
}
