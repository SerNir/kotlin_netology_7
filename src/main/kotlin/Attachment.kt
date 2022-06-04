abstract class Attachment(open val type: String)

data class AudioAttachment(override val type: String = "audio", val audio: Audio) : Attachment(type)
data class VideoAttachment(override val type: String = "video", val video: Video) : Attachment(type)
data class PhotoAttachment(override val type: String = "photo", val photo: Photo) : Attachment(type)
data class DocumentAttachment(override val type: String = "document", val document: Document) : Attachment(type)
data class NoteAttachment(override val type: String = "note", val note: Note) : Attachment(type)

interface Type {
    val id: Int
    val ownerId: Int
    val url: String
}

data class Audio(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val artist: String,
    val title: String?,
    val duration: Int
) : Type

data class Video(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val title: String,
    val description: String?,
    val duration: Int
) : Type

data class Photo(
    override val id: Int,
    override val ownerId: Int,
    override val url: String
) : Type

data class Document(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val title: String?,
    val size: Int
) : Type

data class Note(
    override val id: Int,
    override val ownerId: Int,
    override val url: String,
    val text: String,
    val date: Long
) : Type
