@file:Suppress("unused")

package io.nekohasekai.ktlib.td.core.raw

import td.TdApi.*
import io.nekohasekai.ktlib.td.core.*

/**
 * Returns information about a file
 * This is an offline request
 *
 * @fileId - Identifier of the file to get
 */
suspend fun TdHandler.getFile(
    fileId: Int
) = sync(GetFile(fileId))

suspend fun TdHandler.getFileOrNull(
    fileId: Int
) = syncOrNull(GetFile(fileId))

fun TdHandler.getFileWith(
    fileId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<File>.() -> Unit)? = null
) = send(GetFile(fileId), stackIgnore + 1, submit)

/**
 * Returns information about a file by its remote ID
 * This is an offline request
 * Can be used to register a URL as a file for further uploading, or sending as a message
 * Even the request succeeds, the file can be used only if it is still accessible to the user
 * For example, if the file is from a message, then the message must be not deleted and accessible to the user
 * If the file database is disabled, then the corresponding object with the file must be preloaded by the application
 *
 * @remoteFileId - Remote identifier of the file to get
 * @fileType - File type, if known
 */
suspend fun TdHandler.getRemoteFile(
    remoteFileId: String? = null,
    fileType: FileType? = null
) = sync(GetRemoteFile(remoteFileId, fileType))

suspend fun TdHandler.getRemoteFileOrNull(
    remoteFileId: String? = null,
    fileType: FileType? = null
) = syncOrNull(GetRemoteFile(remoteFileId, fileType))

fun TdHandler.getRemoteFileWith(
    remoteFileId: String? = null,
    fileType: FileType? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<File>.() -> Unit)? = null
) = send(GetRemoteFile(remoteFileId, fileType), stackIgnore + 1, submit)

/**
 * Downloads a file from the cloud
 * Download progress and completion of the download will be notified through updateFile updates
 *
 * @fileId - Identifier of the file to download
 * @priority - Priority of the download (1-32)
 *             The higher the priority, the earlier the file will be downloaded
 *             If the priorities of two files are equal, then the last one for which downloadFile was called will be downloaded first
 * @offset - The starting position from which the file should be downloaded
 * @limit - Number of bytes which should be downloaded starting from the "offset" position before the download will be automatically cancelled
 *          Use 0 to download without a limit
 * @synchronous - If false, this request returns file state just after the download has been started
 *                If true, this request returns file state only after the download has succeeded, has failed, has been cancelled or a new downloadFile request with different offset/limit parameters was sent
 */
suspend fun TdHandler.downloadFile(
    fileId: Int,
    priority: Int,
    offset: Int,
    limit: Int,
    synchronous: Boolean
) = sync(DownloadFile(fileId, priority, offset, limit, synchronous))

suspend fun TdHandler.downloadFileOrNull(
    fileId: Int,
    priority: Int,
    offset: Int,
    limit: Int,
    synchronous: Boolean
) = syncOrNull(DownloadFile(fileId, priority, offset, limit, synchronous))

fun TdHandler.downloadFileWith(
    fileId: Int,
    priority: Int,
    offset: Int,
    limit: Int,
    synchronous: Boolean,
    stackIgnore: Int = 0,
    submit: (TdCallback<File>.() -> Unit)? = null
) = send(DownloadFile(fileId, priority, offset, limit, synchronous), stackIgnore + 1, submit)

/**
 * Returns file downloaded prefix size from a given offset
 *
 * @fileId - Identifier of the file
 * @offset - Offset from which downloaded prefix size should be calculated
 */
suspend fun TdHandler.getFileDownloadedPrefixSize(
    fileId: Int,
    offset: Int
) = sync(GetFileDownloadedPrefixSize(fileId, offset))

suspend fun TdHandler.getFileDownloadedPrefixSizeOrNull(
    fileId: Int,
    offset: Int
) = syncOrNull(GetFileDownloadedPrefixSize(fileId, offset))

fun TdHandler.getFileDownloadedPrefixSizeWith(
    fileId: Int,
    offset: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Count>.() -> Unit)? = null
) = send(GetFileDownloadedPrefixSize(fileId, offset), stackIgnore + 1, submit)

/**
 * Asynchronously uploads a file to the cloud without sending it in a message
 * UpdateFile will be used to notify about upload progress and successful completion of the upload
 * The file will not have a persistent remote identifier until it will be sent in a message
 *
 * @file - File to upload
 * @fileType - File type
 * @priority - Priority of the upload (1-32)
 *             The higher the priority, the earlier the file will be uploaded
 *             If the priorities of two files are equal, then the first one for which uploadFile was called will be uploaded first
 */
suspend fun TdHandler.uploadFile(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int
) = sync(UploadFile(file, fileType, priority))

suspend fun TdHandler.uploadFileOrNull(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int
) = syncOrNull(UploadFile(file, fileType, priority))

fun TdHandler.uploadFileWith(
    file: InputFile? = null,
    fileType: FileType? = null,
    priority: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<File>.() -> Unit)? = null
) = send(UploadFile(file, fileType, priority), stackIgnore + 1, submit)

/**
 * Writes a part of a generated file
 * This method is intended to be used only if the application has no direct access to TDLib's file system, because it is usually slower than a direct write to the destination file
 *
 * @generationId - The identifier of the generation process
 * @offset - The offset from which to write the data to the file
 * @data - The data to write
 */
suspend fun TdHandler.writeGeneratedFilePart(
    generationId: Long,
    offset: Int,
    data: ByteArray
){
    sync(WriteGeneratedFilePart(generationId, offset, data))
}


suspend fun TdHandler.writeGeneratedFilePartIgnoreException(
    generationId: Long,
    offset: Int,
    data: ByteArray
){
    syncOrNull(WriteGeneratedFilePart(generationId, offset, data))
}


fun TdHandler.writeGeneratedFilePartWith(
    generationId: Long,
    offset: Int,
    data: ByteArray,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(WriteGeneratedFilePart(generationId, offset, data), stackIgnore + 1, submit)

/**
 * Informs TDLib on a file generation progress
 *
 * @generationId - The identifier of the generation process
 * @expectedSize - Expected size of the generated file, in bytes
 *                 0 if unknown
 * @localPrefixSize - The number of bytes already generated
 */
suspend fun TdHandler.setFileGenerationProgress(
    generationId: Long,
    expectedSize: Int,
    localPrefixSize: Int
){
    sync(SetFileGenerationProgress(generationId, expectedSize, localPrefixSize))
}


suspend fun TdHandler.setFileGenerationProgressIgnoreException(
    generationId: Long,
    expectedSize: Int,
    localPrefixSize: Int
){
    syncOrNull(SetFileGenerationProgress(generationId, expectedSize, localPrefixSize))
}


fun TdHandler.setFileGenerationProgressWith(
    generationId: Long,
    expectedSize: Int,
    localPrefixSize: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(SetFileGenerationProgress(generationId, expectedSize, localPrefixSize), stackIgnore + 1, submit)

/**
 * Finishes the file generation
 *
 * @generationId - The identifier of the generation process
 * @error - If set, means that file generation has failed and should be terminated
 */
suspend fun TdHandler.finishFileGeneration(
    generationId: Long,
    error: Error? = null
){
    sync(FinishFileGeneration(generationId, error))
}


suspend fun TdHandler.finishFileGenerationIgnoreException(
    generationId: Long,
    error: Error? = null
){
    syncOrNull(FinishFileGeneration(generationId, error))
}


fun TdHandler.finishFileGenerationWith(
    generationId: Long,
    error: Error? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(FinishFileGeneration(generationId, error), stackIgnore + 1, submit)

/**
 * Reads a part of a file from the TDLib file cache and returns read bytes
 * This method is intended to be used only if the application has no direct access to TDLib's file system, because it is usually slower than a direct read from the file
 *
 * @fileId - Identifier of the file
 *           The file must be located in the TDLib file cache
 * @offset - The offset from which to read the file
 * @count - Number of bytes to read
 *          An error will be returned if there are not enough bytes available in the file from the specified position
 *          Pass 0 to read all available data from the specified position
 */
suspend fun TdHandler.readFilePart(
    fileId: Int,
    offset: Int,
    count: Int
) = sync(ReadFilePart(fileId, offset, count))

suspend fun TdHandler.readFilePartOrNull(
    fileId: Int,
    offset: Int,
    count: Int
) = syncOrNull(ReadFilePart(fileId, offset, count))

fun TdHandler.readFilePartWith(
    fileId: Int,
    offset: Int,
    count: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<FilePart>.() -> Unit)? = null
) = send(ReadFilePart(fileId, offset, count), stackIgnore + 1, submit)

/**
 * Deletes a file from the TDLib file cache
 *
 * @fileId - Identifier of the file to delete
 */
suspend fun TdHandler.deleteFile(
    fileId: Int
){
    sync(DeleteFile(fileId))
}


suspend fun TdHandler.deleteFileIgnoreException(
    fileId: Int
){
    syncOrNull(DeleteFile(fileId))
}


fun TdHandler.deleteFileWith(
    fileId: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<Ok>.() -> Unit)? = null
) = send(DeleteFile(fileId), stackIgnore + 1, submit)

/**
 * Returns a file with a segment of a group call stream in a modified OGG format
 *
 * @groupCallId - Group call identifier
 * @timeOffset - Point in time when the stream segment begins
 *               Unix timestamp in milliseconds
 * @scale - Segment duration scale
 *          Segment's duration is 1000/(2**scale) milliseconds
 */
suspend fun TdHandler.getGroupCallStreamSegment(
    groupCallId: Int,
    timeOffset: Long,
    scale: Int
) = sync(GetGroupCallStreamSegment(groupCallId, timeOffset, scale))

suspend fun TdHandler.getGroupCallStreamSegmentOrNull(
    groupCallId: Int,
    timeOffset: Long,
    scale: Int
) = syncOrNull(GetGroupCallStreamSegment(groupCallId, timeOffset, scale))

fun TdHandler.getGroupCallStreamSegmentWith(
    groupCallId: Int,
    timeOffset: Long,
    scale: Int,
    stackIgnore: Int = 0,
    submit: (TdCallback<FilePart>.() -> Unit)? = null
) = send(GetGroupCallStreamSegment(groupCallId, timeOffset, scale), stackIgnore + 1, submit)

/**
 * Uploads a PNG image with a sticker
 * For bots only
 * Returns the uploaded file
 *
 * @userId - Sticker file owner
 * @pngSticker - PNG image with the sticker
 *               Must be up to 512 KB in size and fit in 512x512 square
 */
suspend fun TdHandler.uploadStickerFile(
    userId: Int,
    pngSticker: InputFile? = null
) = sync(UploadStickerFile(userId, pngSticker))

suspend fun TdHandler.uploadStickerFileOrNull(
    userId: Int,
    pngSticker: InputFile? = null
) = syncOrNull(UploadStickerFile(userId, pngSticker))

fun TdHandler.uploadStickerFileWith(
    userId: Int,
    pngSticker: InputFile? = null,
    stackIgnore: Int = 0,
    submit: (TdCallback<File>.() -> Unit)? = null
) = send(UploadStickerFile(userId, pngSticker), stackIgnore + 1, submit)

/**
 * Returns information about a file with a map thumbnail in PNG format
 * Only map thumbnail files with size less than 1MB can be downloaded
 *
 * @location - Location of the map center
 * @zoom - Map zoom level
 * @width - Map width in pixels before applying scale
 * @height - Map height in pixels before applying scale
 * @scale - Map scale
 * @chatId - Identifier of a chat, in which the thumbnail will be shown
 *           Use 0 if unknown
 */
suspend fun TdHandler.getMapThumbnailFile(
    location: Location? = null,
    zoom: Int,
    width: Int,
    height: Int,
    scale: Int,
    chatId: Long
) = sync(GetMapThumbnailFile(location, zoom, width, height, scale, chatId))

suspend fun TdHandler.getMapThumbnailFileOrNull(
    location: Location? = null,
    zoom: Int,
    width: Int,
    height: Int,
    scale: Int,
    chatId: Long
) = syncOrNull(GetMapThumbnailFile(location, zoom, width, height, scale, chatId))

fun TdHandler.getMapThumbnailFileWith(
    location: Location? = null,
    zoom: Int,
    width: Int,
    height: Int,
    scale: Int,
    chatId: Long,
    stackIgnore: Int = 0,
    submit: (TdCallback<File>.() -> Unit)? = null
) = send(GetMapThumbnailFile(location, zoom, width, height, scale, chatId), stackIgnore + 1, submit)
