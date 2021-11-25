package com.sdwtech.mycontact.data

data class ContactResponse(
	val notes: String? = null,
	val phone: String? = null,
	val created: String? = null,
	val name: String? = null,
	val id: Int? = null,
	val email: String? = null,
	val labels: List<LabelsItem?>? = null
)

data class LabelsItem(
	val title: String? = null,
	val slug: String? = null
)

